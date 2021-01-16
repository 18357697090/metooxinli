package com.metoo.web.controller.order;


import com.loongya.core.util.RE;
import com.metoo.api.order.PsCapsuleOrderApi;
import com.metoo.api.ps.PsConsultOrderApi;
import com.metoo.pojo.ps.vo.PsCapsuleVo;
import com.metoo.pojo.ps.vo.PsConsultOrderVo;
import com.metoo.pojo.ps.vo.PsConsultVo;
import com.metoo.web.config.auth.ThreadLocal;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户购买心理咨询师记录表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/psConsultOrder")
public  class PsConsultOrderController {


    @DubboReference
    private PsConsultOrderApi psConsultOrderApi;

    /**
     * 咨询师订单
     * @param vo
     * @return
     */
    @PostMapping("/buyConsult")
    public RE buyConsult(PsConsultOrderVo vo){
        vo.setUserId(ThreadLocal.getUserId());
        return psConsultOrderApi.buyConsult(vo);
    }
    /**
     * 咨询师订单列表
     * @param vo
     * @return
     */
    @PostMapping("/psConsulOrdertList")
    public RE psConsulOrdertList(PsConsultVo vo){
        vo.setUserId(ThreadLocal.getUserId());
        return psConsultOrderApi.psConsulOrdertList(vo);
    }


}
