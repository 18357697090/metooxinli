package com.metoo.user.ta.service;

import com.metoo.user.ta.dao.entity.TaTaskToUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 任务指定用户 服务类
 * </p>
 *
 * @author loongya
 * @since 2021-01-08
 */
public interface TaTaskToUserService extends IService<TaTaskToUser> {

    int countAllByTaskIdAndUserId(Integer taskId, Integer uid);
}
