package com.metoo.web.controller.ps;


import com.loongya.core.util.AssertUtils;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsPourOutApi;
import com.metoo.pojo.ps.vo.PsPourOutVo;
import com.metoo.web.config.auth.ThreadLocal;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 心理倾诉师表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/psPourOut")
public class PsPourOutController {

    @DubboReference
    private PsPourOutApi psPourOutApi;

    /**
     * 倾诉师列表
     * @return
     */
    @GetMapping("/getPourList")
    public RE getPourList(PsPourOutVo vo){
        vo.setUserId(ThreadLocal.getUserId());
        return psPourOutApi.getPourList(vo);
    }
    /**
     * 倾诉师详情
     * @return
     */
    @GetMapping("/getPourDetail")
    public RE getPourDetail(PsPourOutVo vo){
        AssertUtils.checkParam(vo.getPourId());
        vo.setUserId(ThreadLocal.getUserId());
        return psPourOutApi.getPourDetail(vo);
    }



}
