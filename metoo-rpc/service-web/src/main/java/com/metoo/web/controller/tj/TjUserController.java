package com.metoo.web.controller.tj;


import com.loongya.core.util.RE;
import com.metoo.api.tj.TjUserApi;
import com.metoo.pojo.old.model.LoginPojo;
import com.metoo.pojo.old.model.SecretGuardPojo;
import com.metoo.pojo.old.model.signInPojo;
import org.apache.dubbo.config.annotation.DubboReference;
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
@RequestMapping("/tj/tjUser")
public class TjUserController {

    @DubboReference
    private TjUserApi tjUserApi;


    //搜索人，添加好友
    @GetMapping("/findFriend")
    public RE fendFriend(@RequestHeader("UID")Integer uid, String name){
        return tjUserApi.fendFriend(uid, name);

    }


    //注册功能
    @PostMapping("/register")
    public RE register(@RequestBody LoginPojo loginPojo){
        return tjUserApi.register(loginPojo);
    }



    //登录
    @PostMapping("/logIn")
    public RE logIn(@RequestBody signInPojo signInPojo){
        return tjUserApi.logIn(signInPojo);

    }

    //修改密码
    @PostMapping("/modifyPassword")
    public RE modifyPassword(@RequestBody SecretGuardPojo secretGuardPojo){
        return tjUserApi.modifyPassword(secretGuardPojo);

    }

}
