package com.metoo.web.controller.tj;


import com.loongya.core.util.RE;
import com.metoo.api.tj.TjUserApi;
import com.metoo.web.config.auth.ThreadLocal;
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


    /**
     * 获取用户信息
     * ok
     * @return
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/getUserInfo")
    public RE getUserInfo(){
        return tjUserApi.getUserInfo(ThreadLocal.getUserId());
    }


    //搜索人，添加好友
    @PostMapping("/findFriend")
    public RE fendFriend(@RequestHeader("UID")Integer uid, String name){
        return tjUserApi.fendFriend(uid, name);
    }




}
