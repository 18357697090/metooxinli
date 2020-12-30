package com.metoo.web.controller.ps;


import com.loongya.core.util.RE;
import com.metoo.api.ps.PsScaleDetailApi;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 心理测量量表详情表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/psScaleDetail")
public class PsScaleDetailController {

    @DubboReference
    private PsScaleDetailApi psScaleDetailApi;


    //测量详情
    @ApiOperation("测量详情")
    @GetMapping("/gaugedetails")
    public RE gaugedetails(Integer scaleId, @RequestHeader("UID") Integer uid){
        return psScaleDetailApi.gaugedetails(scaleId, uid);

    }

}
