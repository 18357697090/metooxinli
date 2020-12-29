package com.metoo.web.controller.xy;


import com.loongya.core.util.RE;
import com.metoo.api.xy.XyRaceApi;
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
 * 国度，族表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/xy/xyRace")
public class XyRaceController {

    @DubboReference
    private XyRaceApi xyRaceApi;

    @ApiOperation("获取族")
    @GetMapping("/getRace")
    public RE getRace(@RequestHeader("UID")Integer uid){
        return xyRaceApi.getRace(uid);

    }

}
