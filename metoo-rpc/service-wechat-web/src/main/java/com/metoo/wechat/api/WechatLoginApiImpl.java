package com.metoo.wechat.api;


import com.alibaba.fastjson.JSONObject;
import com.loongya.core.exception.LoongyaException;
import com.loongya.core.util.ConstantUtil;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.loongya.core.util.http.HttpClientUtil;
import com.metoo.api.tj.TjUserApi;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.api.wechat.login.WechatLoginApi;
import com.metoo.pojo.login.enums.AuthEnum;
import com.metoo.pojo.login.model.LoginModel;
import com.metoo.pojo.login.vo.LoginVo;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.pojo.tj.model.TjUserModel;
import com.metoo.pojo.tj.vo.TjUserInfoVo;
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
    @DubboReference
    private TjUserInfoApi tjUserInfoApi;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public RE login(LoginVo vo) {
        Integer userId = null;
        String extendId = null;
        // 微信登录获取openId
        WechatLoginModel model = wechatLogin(vo);
        // 根据openId获取用户信息
        RE userResult = tjUserApi.findByOpenId(model.getOpenId());
        vo.setOpenId(model.getOpenId());
        if(userResult.isFail()){
            // 如果没有数据,返回,绑定迷途心理账号,或者直接注册新账号
            return register(vo);
        }else {
            TjUserModel userModel = (TjUserModel) userResult.getData();
            userId = userModel.getId();
            extendId = userModel.getExtendId();
            return getToken(userId, extendId);
        }
    }

    private RE getToken(Integer userId, String extendId) {
        String randomKey = jwtTokenUtil.getRandomKey();
        String token = jwtTokenUtil.generateToken(userId+"", randomKey);
        return RE.ok(new AuthResponse(token, extendId, randomKey));
    }

    private void updateUserInfo(Integer userId, LoginVo vo) {
        TjUserInfoVo userInfoModel = new TjUserInfoVo();
        userInfoModel.setUserId(userId);
        userInfoModel.setHeadImg(vo.getHeadImg());
        userInfoModel.setNickName(vo.getNickName());
        userInfoModel.setGender(vo.getGender());
        userInfoModel.setProv(vo.getProvince());
        userInfoModel.setCity(vo.getCity());
        tjUserInfoApi.upLoadUserInfo(userInfoModel);
    }

    @Override
    public RE register(LoginVo vo) {
        Integer userId = null;
        String extendId = null;
        // 新注册用户
        vo.setUsername(vo.getOpenId());
        vo.setPassword(ConstantUtil.DEFAULT_PASSWORD);
        vo.setRepeatPassword(vo.getPassword());
        RE registerRe  = tjUserApi.register(vo);
        if(registerRe.isFail()) {
            return registerRe;
        }
        LoginModel loginModel = (LoginModel) registerRe.getData();
        userId = loginModel.getUserId();
        extendId = loginModel.getExtendId();
        updateUserInfo(userId, vo);
        // 获取assectToken逻辑
        // 获取token
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+wechatProperties.getAppid() + "&secret=" +wechatProperties.getSecret();
        log.info("微信获取token:{}", tokenUrl);
        String tokenResult = HttpClientUtil.getHttp(tokenUrl);
        log.info("微信获取token结果: {}", tokenResult);
        JSONObject tokenjson = JSONObject.parseObject(tokenResult);
        String assectToken = tokenjson.getString("access_token");
        long expiresIn = tokenjson.getLong("expires_in");
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(ConstantUtil.REDIS_ASSECTTOKEN_KEY, assectToken, expiresIn);
        // 定时任务,定时刷新token,保存到数据库 todo.
        return getToken(userId, extendId);
    }

    private WechatLoginModel wechatLogin(LoginVo vo) {
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
        try {
            //"{"session_key":"3zH27RCqCkzQxXBBKAt3ng==","openid":"oVteb5RoFy_xMt5Ub6Qpgk_0H4h8"}"
            WechatLoginModel model = JSONObject.parseObject(http, WechatLoginModel.class);
            if(OU.isBlack(model)){
                throw new Exception("登录失败");
            }
            return model;
        }catch (Exception e){
            e.printStackTrace();
            throw new LoongyaException("登录失败!");
        }

    }
}
