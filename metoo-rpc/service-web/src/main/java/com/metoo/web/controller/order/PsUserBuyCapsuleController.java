package com.metoo.web.controller.order;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * <p>
 * 用户购买胶囊记录表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/ps-user-buy-capsule")
public class PsUserBuyCapsuleController {


    @GetMapping("/pay")
    public String pay(@RequestHeader("UID") Integer uid, Integer capsuleId){
        Zh zh = zhDao.findByUid(uid);
        Capsule capsule = capsuleDao.findByCapsuleId(capsuleId);
        BigDecimal prices = capsule.getPrices();
        int x = zh.getBalance().compareTo(prices);
        if(x >= 0){
            zhDao.updateBalance(zh.getBalance().subtract(prices),uid);
            UserBuyCapsule userBuyCapsule = new UserBuyCapsule();
            userBuyCapsule.setCapsuleId(capsuleId);
            userBuyCapsule.setUid(uid);
            userBuyCapsuleDao.save(userBuyCapsule);
            return "success";
        }else {
            return "error";
        }
    }

}
