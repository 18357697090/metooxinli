package com.metoo.web.controller.login;


import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loongya.core.util.AssertUtils;
import com.loongya.core.util.CommsEnum;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.login.LoginApi;
import com.metoo.api.tj.TjUserApi;
import com.metoo.pojo.login.model.LoginModel;
import com.metoo.pojo.login.model.LoginUserInfoModel;
import com.metoo.pojo.login.vo.LoginUploadPasswordVo;
import com.metoo.pojo.login.vo.LoginVo;
import com.metoo.pojo.old.model.LoginPojo;
import com.metoo.pojo.old.model.SecretGuardPojo;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.pojo.tj.model.TjUserModel;
import com.metoo.web.config.tools.JwtTokenUtil;
import com.metoo.web.controller.login.dto.AuthResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
public class LoginController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @DubboReference
    private TjUserApi tjUserApi;

    @RequestMapping(value = "${jwt.auth-path}")
    public RE createAuthenticationToken(LoginVo vo) {
        return getToken(tjUserApi.logIn(vo));
    }

    //注册功能
    @PostMapping("/register")
    public RE register(LoginVo vo){
        RE re = AssertUtils.checkParam(vo.getUsername(), vo.getPassword(), vo.getRepeatPassword());
        if(re.isFail()){
            return RE.fail(CommsEnum.PARAM_ERR);
        }
        return getToken(tjUserApi.register(vo));
    }


    //修改密码
    @PostMapping("/modifyPassword")
    public RE modifyPassword(LoginUploadPasswordVo vo){
        RE re = AssertUtils.checkParam(vo.getUsername(), vo.getPassword(), vo.getRepeatPassword(), vo.getAnswer());
        if(re.isFail()){
            return RE.fail(CommsEnum.PARAM_ERR);
        }
        return tjUserApi.modifyPassword(vo);
    }


    public RE getToken(RE re){
        if(re.isFail()){
            return re;
        }
        LoginModel loginModel = (LoginModel) re.getData();
        // randomKey和token已经生成完毕
        final String randomKey = jwtTokenUtil.getRandomKey();
        final String token = jwtTokenUtil.generateToken(loginModel.getUserId()+"", randomKey);
        return RE.ok(new AuthResponse(token, loginModel.getExtendId(), randomKey));
    }


}
