package com.metoo.user.ta.service;

import com.metoo.user.ta.dao.entity.TaTaskToLevel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 任务指定等级 服务类
 * </p>
 *
 * @author loongya
 * @since 2021-01-08
 */
public interface TaTaskToLevelService extends IService<TaTaskToLevel> {

    int countAllByTaskIdAndLevel(Integer id, Integer level);
}
