package com.metoo.web.controller.order;


import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsScaleApi;
import com.metoo.api.ps.PsScaleMeasureRecordApi;
import com.metoo.pojo.login.enums.AuthEnum;
import com.metoo.pojo.ps.vo.PsScaleVo;
import com.metoo.web.config.auth.ThreadLocal;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户心理测量记录表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/psScaleOrder")
public class PsScaleOrderController {


    @DubboReference
    private PsScaleMeasureRecordApi psScaleMeasureRecordApi;


    /**
     * 心理测试购买
     */
    @PostMapping("/buyScale")
    public RE buyScale(PsScaleVo vo){
        Integer userId = ThreadLocal.getUserId();
        if(OU.isBlack(userId)){
            return RE.fail(AuthEnum.LOGIN_TIMEOUT);
        }
        return psScaleMeasureRecordApi.pay(userId, vo.getScaleId());
    }

}
