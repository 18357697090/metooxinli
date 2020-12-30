package com.metoo.web.controller.ps;


import com.loongya.core.util.RE;
import com.metoo.api.ps.PsMeasureRecordApi;
import com.metoo.api.ps.PsUserAndMeasureApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户心理测量量表记录表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/psMeasureRecord")
public class PsMeasureRecordController {

    @DubboReference
    private PsMeasureRecordApi psMeasureRecordApi;


    @GetMapping("/measureRecord")
    public RE measureRecord(@RequestHeader("UID")Integer uid, String time){
        return psMeasureRecordApi.measureRecord(uid, time);

    }

}
