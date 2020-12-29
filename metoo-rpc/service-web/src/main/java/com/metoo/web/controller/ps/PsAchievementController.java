package com.metoo.web.controller.ps;


import com.metoo.api.ps.PsAchievementApi;
import com.metoo.pojo.old.model.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 心理测量成绩表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/psAchievement")
public class PsAchievementController {

    @DubboReference
    private PsAchievementApi psAchievementApi;


    //返回测量分数
    @ApiOperation("测量完成后的返回结果")
    @PostMapping("/result")
    public String result(@RequestBody Result results, @RequestHeader("UID")Integer uid){
        return psAchievementApi.result(results, uid);

    }

}
