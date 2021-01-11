package com.metoo.user.ta.service;

import com.metoo.user.ta.dao.entity.TaTaskUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户我的任务表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-29
 */
public interface TaTaskUserService extends IService<TaTaskUser> {


    Long countByUidAndTaskId(Integer uid, Integer taskId);

    TaTaskUser findFirstByUidAndTaskId(Integer uid, Integer id);

    List<TaTaskUser> findAllByTaskId(Integer id);

    Integer countByTaskId(Integer id);
}
