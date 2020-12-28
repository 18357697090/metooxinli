package com.metoo.web.controller.ps;


import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/ps/ps-achievement")
public class PsAchievementController {

    //返回测量分数
    @ApiOperation("测量完成后的返回结果")
    @PostMapping("/result")
    public String result(@RequestBody Result results, @RequestHeader("UID")Integer uid){
        userAndMeasureDao.updateMeasure(uid,results.getScaleId());
        String achievement=CalculateTheScore.calculate(results);
//        Achievement achievement1=new Achievement();
//        achievement1.setAchievement(achievement);
//        achievement1.setUid(uid);
//        achievement1.setScaleId(results.getScaleId());
//        achievementDao.save(achievement1);
        return achievement;
    }

}
