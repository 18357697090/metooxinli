package com.metoo.web.controller.login;


import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loongya.core.util.RE;
import com.metoo.api.login.LoginApi;
import com.metoo.api.tj.TjUserApi;
import com.metoo.pojo.login.vo.LoginVo;
import com.metoo.pojo.old.model.LoginPojo;
import com.metoo.pojo.old.model.SecretGuardPojo;
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

        boolean validate = true;
        // 去掉guns自身携带的用户名密码验证机制，使用我们自己的
        RE re =  tjUserApi.logIn(vo);
        if(re.getCode() != 0){
            validate = false;
        }

        if (validate) {
            // randomKey和token已经生成完毕
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(""+1, randomKey);
            // 返回值
            return RE.ok(new AuthResponse(token, randomKey));
        } else {
            return RE.fail("用户名或密码错误");
        }
    }

    //注册功能
    @PostMapping("/register")
    public RE register(@RequestBody LoginPojo loginPojo){
        return tjUserApi.register(loginPojo);
    }


    //修改密码
    @PostMapping("/modifyPassword")
    public RE modifyPassword(@RequestBody SecretGuardPojo secretGuardPojo){
        return tjUserApi.modifyPassword(secretGuardPojo);
    }

}
