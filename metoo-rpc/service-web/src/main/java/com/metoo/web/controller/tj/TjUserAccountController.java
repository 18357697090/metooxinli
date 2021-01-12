package com.metoo.web.controller.tj;


import com.loongya.core.util.RE;
import com.metoo.api.tj.TjUserAccountApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 用户账户表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/tj/tjUserAccount")
public class TjUserAccountController {

    @DubboReference
    private TjUserAccountApi tjUserAccountApi;

    @PostMapping("/me")
    public RE me(@RequestHeader("UID")Integer uid){
        return tjUserAccountApi.me(uid);
    }

    //获取个人账户信息
    @PostMapping("/findzh")
    public RE findzh(Integer uid){
        return tjUserAccountApi.findzh(uid);
    }


    //查找账户余额
    @PostMapping("/findBalance")
    public RE findBalance(@RequestHeader("UID") Integer uid){
        return tjUserAccountApi.findBalance(uid);
    }



}
