package com.metoo.web.controller.order;


import com.loongya.core.util.AssertUtils;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsPourOutOrderApi;
import com.metoo.pojo.ps.vo.PsCapsuleVo;
import com.metoo.pojo.ps.vo.PsPourOutVo;
import com.metoo.web.config.auth.ThreadLocal;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 倾诉师订单 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2021-01-07
 */
@RestController
@RequestMapping("/ps/psPourOutOrder")
public class PsPourOutOrderController {

    @DubboReference
    private PsPourOutOrderApi psPourOutOrderApi;

    /**
     * 倾诉师订单
     * @param vo
     * @return
     */
    @PostMapping("/buyPour")
    public RE buyPour(PsPourOutVo vo){
        AssertUtils.checkParam(vo.getPourId());
        vo.setUserId(ThreadLocal.getUserId());
        return psPourOutOrderApi.buyPour(vo);
    }


    /**
     * 倾诉师订单列表
     * @return
     */
    @PostMapping("/getPourOrderList")
    public RE getPourOrderList(PsPourOutVo vo){
        vo.setUserId(ThreadLocal.getUserId());
        return psPourOutOrderApi.getPourOrderList(vo);
    }
}
