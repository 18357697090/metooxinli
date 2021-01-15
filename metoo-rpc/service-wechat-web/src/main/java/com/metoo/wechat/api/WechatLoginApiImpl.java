package com.metoo.wechat.api;


import com.alibaba.fastjson.JSONObject;
import com.loongya.core.exception.LoongyaException;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.loongya.core.util.http.HttpClientUtil;
import com.metoo.api.tj.TjUserApi;
import com.metoo.api.wechat.login.WechatLoginApi;
import com.metoo.pojo.login.enums.AuthEnum;
import com.metoo.pojo.login.model.LoginModel;
import com.metoo.pojo.login.vo.LoginVo;
import com.metoo.pojo.tj.model.TjUserModel;
import com.metoo.pojo.wechat.tj.login.model.WechatLoginModel;
import com.metoo.wechat.config.properties.WechatProperties;
import com.metoo.wechat.config.tools.JwtTokenUtil;
import com.metoo.wechat.controller.login.dto.AuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WechatLoginApiImpl implements WechatLoginApi {

    @Autowired
    private WechatProperties wechatProperties;

    @DubboReference
    private TjUserApi tjUserApi;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public RE login(LoginVo vo) {
        StringBuilder sb = new StringBuilder();
        sb.append(wechatProperties.getLoginUrl())
                .append("?")
                .append("appid=")
                .append(wechatProperties.getAppid())
                .append("&secret=")
                .append(wechatProperties.getSecret())
                .append("&js_code=")
                .append(vo.getJsCode())
                .append("&grant_type=")
                .append(wechatProperties.getGrantType());
        log.info("微信登录url:{}", sb.toString());
        String http = HttpClientUtil.getHttp(sb.toString());
        log.info("微信登录结果: {}", http);
        WechatLoginModel model = null;
        try {
            //"{"session_key":"3zH27RCqCkzQxXBBKAt3ng==","openid":"oVteb5RoFy_xMt5Ub6Qpgk_0H4h8"}"
            model = JSONObject.parseObject(http, WechatLoginModel.class);
            if(OU.isBlack(model)){
                throw new Exception("登录失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new LoongyaException("登录失败!");
        }
        RE userResult = tjUserApi.findByOpenId(model.getOpenId());
        Integer userId = null;
        String extendId = null;
        if(userResult.isFail()){
            if(OU.isBlack(vo.getUsername())){
                // 没有登录过,返回,唤起账号密码登录
                return RE.fail(AuthEnum.LOGIN_TIMEOUT);
            }
            LoginVo loginVo = new LoginVo();
            loginVo.setUsername(vo.getUsername());
            loginVo.setPassword(vo.getPassword());
            RE re = tjUserApi.logIn(loginVo);
            if(re.isFail()){
                return re;
            }
            LoginModel loginModel = (LoginModel) re.getData();
            userId = loginModel.getUserId();
            extendId = loginModel.getExtendId();
        }else {
            TjUserModel userModel = (TjUserModel) userResult.getData();
            userId = userModel.getId();
            extendId = userModel.getExtendId();
        }
        // 登录
        model.setUid(userId);
        // 获取token
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+wechatProperties.getAppid() + "&secret=" +wechatProperties.getSecret();
        log.info("微信获取token:{}", tokenUrl);
        String tokenResult = HttpClientUtil.getHttp(tokenUrl);
        log.info("微信获取token结果: {}", tokenResult);
        JSONObject tokenjson = JSONObject.parseObject(tokenResult);
        String assectToken = tokenjson.getString("access_token");
        long expiresIn = tokenjson.getLong("expires_in");
        model.setAssectToken(assectToken);
        tjUserApi.saveToken(model);
        final String randomKey = jwtTokenUtil.getRandomKey();
        final String token = jwtTokenUtil.generateToken(userId+"", randomKey);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("wechat:assecttoken:uid:" + userId, assectToken, expiresIn);
        // 定时任务,定时刷新token,保存到数据库 todo.

        return RE.ok(new AuthResponse(token, extendId, randomKey));
    }
}
