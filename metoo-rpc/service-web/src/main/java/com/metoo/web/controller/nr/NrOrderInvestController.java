package com.metoo.web.controller.nr;


import com.loongya.core.util.AssertUtils;
import com.loongya.core.util.RE;
import com.metoo.api.order.NrOrderInvestApi;
import com.metoo.pojo.nr.vo.NrOrderInvestVo;
import com.metoo.web.config.auth.ThreadLocal;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 充值订单表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2021-01-13
 */
@RestController
@RequestMapping("/nr/nrOrderInvest")
public class NrOrderInvestController {

    @DubboReference
    private NrOrderInvestApi nrOrderInvestApi;

    /**
     * 生成订单
     * 订单开头: IVS
     * @param vo
     * @return
     */
    @PostMapping("/createInvestOrder")
    public RE createInvestOrder(NrOrderInvestVo vo){
        AssertUtils.checkParam(vo.getPrice());
        vo.setUid(ThreadLocal.getUserId());
        return nrOrderInvestApi.createInvestOrder(vo);
    }
}
