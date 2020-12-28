package com.metoo.web.controller.ps;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("/ps/ps-scale-gather")
public class PsScaleGatherController {

    @GetMapping("/clgather")
    @ApiOperation("推荐测量")
    public List<ScaleGather> clgather(int uid){
        UserInfo userInfo = userInfoDao.findByUid(uid);
        int dw=1;
        if(userInfo !=null){
            dw= userInfo.getDw();
        }
        List<ScaleGather> scaleGathers =new ArrayList<>();
        scaleGathers.add(scaleGatherDao.findByScaleGatherId(dw));
        scaleGathers.add(scaleGatherDao.findByScaleGatherId(101));
        scaleGathers.add(scaleGatherDao.findByScaleGatherId(102));
        return scaleGathers;
    }

    //所有测量集合
    @GetMapping("/clgatherall")
    @ApiOperation("更多推荐测量")
    public List<ScaleGather> clgaherall(){
        return scaleGatherDao.findByScaleGatherIdAll();
    }

    @GetMapping("/findbyclgatherid")
    @ApiOperation("推荐测量集合内容")
    public List<Scale> findbyclgatherid(@ApiParam("测量集合的id")Integer clgatherid){
        return scaleDao.findByScaleGatherId(clgatherid);
    }

}
