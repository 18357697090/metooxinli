package com.metoo.user.ta.service.impl;

import com.metoo.pojo.ta.model.TaTaskModel;
import com.metoo.pojo.ta.vo.TaTaskVo;
import com.metoo.user.ta.dao.entity.TaTask;
import com.metoo.user.ta.dao.mapper.TaTaskMapper;
import com.metoo.user.ta.dao.repository.TaTaskRepository;
import com.metoo.user.ta.service.TaTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 任务大厅表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class TaTaskServiceImpl extends ServiceImpl<TaTaskMapper, TaTask> implements TaTaskService {

    @Autowired
    private TaTaskRepository taTaskRepository;

    @Resource
    private TaTaskMapper taTaskMapper;

    @Override
    public List<TaTask> findOrdinaryTask(Pageable pageable) {
        return taTaskRepository.findOrdinaryTask(pageable);
    }

    @Override
    public List<TaTask> findTutorialTask(Pageable pageable) {
        return taTaskRepository.findTutorialTask(pageable);
    }

    @Override
    public TaTask findByTaskId(Integer taskId) {
        return taTaskRepository.findByTaskId(taskId);
    }

    @Override
    public void updateTaskState(Integer taskId) {
        taTaskRepository.updateTaskState(taskId);
    }

    @Override
    public List<TaTaskModel> taskList(TaTaskVo vo) {
        return taTaskMapper.taskList(vo);
    }
}
