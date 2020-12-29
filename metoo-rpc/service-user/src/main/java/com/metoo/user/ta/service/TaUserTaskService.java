package com.metoo.user.ta.service;

import com.metoo.user.ta.dao.entity.TaUserTask;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户我的任务表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-29
 */
public interface TaUserTaskService extends IService<TaUserTask> {

    void updateAcceptId(Integer uid, Integer taskId);

    int updateAcceptState(Integer taskId, Integer uid);
}
