package com.metoo.web.controller.nr;


import com.loongya.core.util.RE;
import com.metoo.api.im.ImUserMessageApi;
import com.metoo.api.nr.NrGoodsApi;
import com.metoo.pojo.im.model.ImUserMessageModel;
import com.metoo.pojo.nr.vo.NrGoodsVo;
import com.metoo.web.config.auth.ThreadLocal;
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

    /**
     * 获取商城道具列表
     * @return
     */
    @GetMapping("/getGoodsList")
    public RE getGoodsList(NrGoodsVo vo){
        vo.setUserId(ThreadLocal.getUserId());
        return nrGoodsApi.getGoodsList(vo);
    }



}
