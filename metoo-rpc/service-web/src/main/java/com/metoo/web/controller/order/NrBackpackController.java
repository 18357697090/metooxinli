package com.metoo.web.controller.order;


import com.loongya.core.util.RE;
import com.metoo.api.order.NrBackpackApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户背包商品表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/nr/nrBackpack")
public class NrBackpackController {

    @DubboReference
    private NrBackpackApi nrBackpackApi;

    @GetMapping("/backpack")
    public RE backpack(@RequestHeader("UID")Integer uid ){
        return nrBackpackApi.backpack(uid);
    }


    @GetMapping("/buy")
    public RE buy(@RequestHeader("UID")Integer uid,Integer type){
        return nrBackpackApi.buy(uid, type);
    }

    @GetMapping("/give")
    public RE give(@RequestHeader("UID")Integer uid,Integer type,Integer donee){
        return nrBackpackApi.give(uid, type, donee);
    }



}
