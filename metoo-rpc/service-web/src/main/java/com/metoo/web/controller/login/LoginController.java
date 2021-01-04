package com.metoo.web.controller.login;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.loongya.core.util.RE;
import com.metoo.api.tj.TjUserApi;
import com.metoo.pojo.login.vo.LoginVo;
import com.metoo.pojo.old.model.LoginPojo;
import com.metoo.pojo.old.model.SecretGuardPojo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
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

    @DubboReference
    private TjUserApi tjUserApi;

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
