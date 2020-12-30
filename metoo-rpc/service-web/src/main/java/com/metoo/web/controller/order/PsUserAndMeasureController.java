package com.metoo.web.controller.order;


import com.loongya.core.util.RE;
import com.metoo.api.ps.PsUserAndMeasureApi;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
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
@RequestMapping("/ps/psUserAndMeasure")
public class PsUserAndMeasureController {


    @DubboReference
    private PsUserAndMeasureApi psUserAndMeasureApi;


    //支付
    @ApiOperation("支付")
    @GetMapping("/pay")
    public RE pay(Integer uid, Integer scaleId){
        return psUserAndMeasureApi.pay(uid, scaleId);


    }


}
