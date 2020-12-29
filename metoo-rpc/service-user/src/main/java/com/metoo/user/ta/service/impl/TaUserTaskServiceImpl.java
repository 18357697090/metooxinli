package com.metoo.user.ta.service.impl;

import com.metoo.user.ta.dao.entity.TaUserTask;
import com.metoo.user.ta.dao.mapper.TaUserTaskMapper;
import com.metoo.user.ta.dao.repository.TaUserTaskRepository;
import com.metoo.user.ta.service.TaUserTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户我的任务表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-29
 */
@Service
public class TaUserTaskServiceImpl extends ServiceImpl<TaUserTaskMapper, TaUserTask> implements TaUserTaskService {

    @Autowired
    private TaUserTaskRepository taUserTaskRepository;

    @Override
    public void updateAcceptId(Integer uid, Integer taskId) {
        taUserTaskRepository.updateAcceptId(uid, taskId);
    }

    @Override
    public int updateAcceptState(Integer taskId, Integer uid) {
        return taUserTaskRepository.updateAcceptState(taskId, uid);
    }
}
