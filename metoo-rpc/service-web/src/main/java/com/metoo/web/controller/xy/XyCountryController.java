package com.metoo.web.controller.xy;


import com.loongya.core.util.RE;
import com.metoo.api.xy.XyCountryApi;
import com.metoo.pojo.old.vo.BuildCountryDTO;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 国度表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/xy/xyCountry")
public class XyCountryController {

    @DubboReference
    private XyCountryApi xyCountryApi;

    @ApiOperation("创建国家")
    @PostMapping("/buildCountry")
    public RE buildCountry(@RequestBody BuildCountryDTO buildCountryDTO, @RequestHeader("UID")Integer uid){
        return xyCountryApi.buildCountry(buildCountryDTO, uid);
    }

    @ApiOperation("获取国家列表")
    @GetMapping("/getCountry")
    public RE getCountry(@RequestHeader("UID")Integer uid, Integer raceId){
        return xyCountryApi.getCountry(uid, raceId);
    }

}
