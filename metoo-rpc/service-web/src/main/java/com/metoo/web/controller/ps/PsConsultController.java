package com.metoo.web.controller.ps;


import com.loongya.core.util.AssertUtils;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsConsultApi;
import com.metoo.pojo.ps.vo.PsConsultVo;
import com.metoo.web.config.auth.ThreadLocal;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 心理咨询师表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/psConsult")
public class PsConsultController {

    @DubboReference
    private PsConsultApi psConsultApi;

    /**
     * 咨询师列表页面bannerList
     * @param vo
     * @return
     */
    @PostMapping("/psConsultBannerList")
    public RE psConsultBannerList(PsConsultVo vo){
        return psConsultApi.psConsultBannerList(vo);
    }
    /**
     * 咨询师列表
     * @param vo
     * @return
     */
    @PostMapping("/psConsultList")
    public RE psConsultList(PsConsultVo vo){
        vo.setUserId(ThreadLocal.getUserId());
        return psConsultApi.psConsultList(vo);
    }
    /**
     * 咨询师详情
     * @param vo
     * @return
     */
    @PostMapping("/psConsultDetail")
    public RE psConsultDetail(PsConsultVo vo){
        AssertUtils.checkParam(vo.getConId());
        vo.setUserId(ThreadLocal.getUserId());
        return psConsultApi.psConsultDetail(vo);
    }

}
