package com.metoo.web.controller.order;


import com.loongya.core.util.RE;
import com.metoo.api.order.PsCapsuleOrderApi;
import com.metoo.pojo.ps.vo.PsCapsuleVo;
import com.metoo.web.config.auth.ThreadLocal;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户购买胶囊记录表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/psCapsuleOrder")
public  class PsCapsuleOrderController {


    @DubboReference
    private PsCapsuleOrderApi psCapsuleOrderApi;

    /**
     * 胶囊购买
     * @param vo
     * @return
     */
    @PostMapping("/pay")
    public RE pay(PsCapsuleVo vo){
        vo.setUserId(ThreadLocal.getUserId());
        return psCapsuleOrderApi.payByCoin(vo);
    }

}
