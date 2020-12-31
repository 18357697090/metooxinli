package com.metoo.web.controller.tj;


import com.loongya.core.util.RE;
import com.metoo.api.tj.TjUserApi;
import com.metoo.pojo.tj.model.TjUserModel;
import com.metoo.web.config.tools.ThreadLocal;
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

    /**
     * 获取用户信息
     * ok
     * @return
     */
    @PostMapping("/getUserInfo")
    public RE getUserInfo(){
        return tjUserApi.getUserInfo(ThreadLocal.getUserId());
    }




}
