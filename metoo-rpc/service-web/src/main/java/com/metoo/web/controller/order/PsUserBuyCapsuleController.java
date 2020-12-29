package com.metoo.web.controller.order;


import com.loongya.core.util.RE;
import com.metoo.api.order.PsUserBuyCapsuleApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户购买胶囊记录表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/psUserBuyCapsule")
public class PsUserBuyCapsuleController {


    @DubboReference
    private PsUserBuyCapsuleApi psUserBuyCapsuleApi;

    @GetMapping("/pay")
    public RE pay(@RequestHeader("UID") Integer uid, Integer capsuleId){
        return psUserBuyCapsuleApi.pay(uid, capsuleId);
    }

}
