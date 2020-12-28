package com.metoo.web.controller.ps;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * <p>
 * 用户心理测量记录表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/ps-user-and-measure")
public class PsUserAndMeasureController {
    //支付
    @ApiOperation("支付")
    @GetMapping("/pay")
    public String pay(Integer uid,Integer scaleId){
        Scale scale = scaleDao.findByScaleId(scaleId);
        Zh zh=zhDao.findByUid(uid);
        int x =zh.getBalance().compareTo(scale.getPrices());
        if(x >= 0) {
            BigDecimal balance = zh.getBalance().subtract(scale.getPrices());
            zhDao.updateBalance(balance, uid);
            UserAndMeasure userAndMeasure=userAndMeasureDao.findByUidAndScaleId(uid,scaleId);
            if(userAndMeasure==null){
                UserAndMeasure userAndMeasure1 = new UserAndMeasure();
                userAndMeasure1.setScaleId(scaleId);
                userAndMeasure1.setUid(uid);
                userAndMeasure1.setState(1);
                userAndMeasure1.setCount(1);
                userAndMeasureDao.save(userAndMeasure1);
            }else {
                userAndMeasureDao.updateCount(userAndMeasure.getCount()+1,uid,scaleId);
            }
            int number=scale.getNumber()+1;
            scaleDao.updateNumber(number,scaleId);

            return "success";
        }else {
            return "error";//error表示余额不足
        }
    }


}
