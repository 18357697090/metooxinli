package com.metoo.web.controller.ps;


import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsScaleApi;
import com.metoo.api.ps.PsScaleGatherApi;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 心理测量量表集合表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/psScaleGather")
public class PsScaleGatherController {

    @DubboReference
    private PsScaleGatherApi psScaleGatherApi;

    @DubboReference
    private PsScaleApi psScaleApi;

    @GetMapping("/clgather")
    @ApiOperation("推荐测量")
    public RE clgather(Integer uid){
        return psScaleGatherApi.clgather(uid);
    }

    //所有测量集合
    @GetMapping("/clgatherall")
    @ApiOperation("更多推荐测量")
    public RE clgaherall(){
        return psScaleGatherApi.clgaherall();
    }

    @GetMapping("/findbyclgatherid")
    @ApiOperation("推荐测量集合内容")
    public RE findbyclgatherid(@ApiParam("测量集合的id")Integer clgatherid){
        return psScaleApi.findbyclgatherid(clgatherid);
    }

}
