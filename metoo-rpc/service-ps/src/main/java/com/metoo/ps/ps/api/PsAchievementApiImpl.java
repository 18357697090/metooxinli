package com.metoo.ps.ps.api;

import com.metoo.api.ps.PsAchievementApi;
import com.metoo.pojo.old.model.Result;
import com.metoo.ps.ps.service.PsAchievementService;
import com.metoo.ps.ps.service.PsUserAndMeasureService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 心理测量成绩表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public class PsAchievementApiImpl implements PsAchievementApi {

    @Autowired
    private PsAchievementService psAchievementService;

    @Autowired
    private PsUserAndMeasureService psUserAndMeasureService;

    @Override
    public String result(Result results, Integer uid) {

        psUserAndMeasureService.updateMeasure(uid,results.getScaleId());
        String achievement=CalculateTheScore.calculate(results);
//        Achievement achievement1=new Achievement();
//        achievement1.setAchievement(achievement);
//        achievement1.setUid(uid);
//        achievement1.setScaleId(results.getScaleId());
//        achievementDao.save(achievement1);
        return achievement;
    }
}
