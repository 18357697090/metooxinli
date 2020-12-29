package com.metoo.api.ps;

import com.metoo.pojo.old.model.Result;

/**
 * <p>
 * 心理测量成绩表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsAchievementApi {

    String result(Result results, Integer uid);
}
