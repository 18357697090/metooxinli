package com.metoo.web.controller.nr;


import com.loongya.core.util.RE;
import com.metoo.api.im.ImUserMessageApi;
import com.metoo.api.nr.NrGoodsApi;
import com.metoo.pojo.im.model.ImUserMessageModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/nr/nrGoods")
public class NrGoodsController {

    @DubboReference
    private NrGoodsApi nrGoodsApi;
    @DubboReference
    private ImUserMessageApi imUserMessageApi;

    @GetMapping("/goods")
    public RE shop(){
        return nrGoodsApi.findAll();
    }



}
