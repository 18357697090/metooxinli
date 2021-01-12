package com.metoo.web.controller.order;


import com.loongya.core.util.AssertUtils;
import com.loongya.core.util.RE;
import com.metoo.api.order.NrBackpackApi;
import com.metoo.pojo.nr.vo.NrGoodsVo;
import com.metoo.pojo.order.vo.NrBackpackVo;
import com.metoo.web.config.auth.ThreadLocal;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
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

    /**
     * 购买道具接口
     * @return
     */
    @PostMapping("/buyGoods")
    public RE buyGoods(NrGoodsVo vo){
        AssertUtils.checkParam(vo.getGoodsId());
        vo.setUserId(ThreadLocal.getUserId());
        return nrBackpackApi.buyGoods(vo);
    }

    /**
     * 购买+赠送道具接口
     * @return
     */
    @PostMapping("/buyAndGiveGoods")
    public RE buyAndGiveGoods(NrGoodsVo vo){
        AssertUtils.checkParam(vo.getExtendId(), vo.getGoodsId());
        vo.setUserId(ThreadLocal.getUserId());
        return nrBackpackApi.buyAndGiveGoods(vo);
    }

    /**
     * 赠送道具接口
     * @return
     */
    @PostMapping("/giveGoods")
    public RE giveGoods(NrGoodsVo vo){
        AssertUtils.checkParam(vo.getExtendId(), vo.getGoodsId());
        vo.setUserId(ThreadLocal.getUserId());
        return nrBackpackApi.giveGoods(vo);
    }



}
