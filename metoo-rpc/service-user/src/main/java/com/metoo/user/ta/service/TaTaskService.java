package com.metoo.user.ta.service;

import com.metoo.user.ta.dao.entity.TaTask;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p>
 * 任务大厅表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface TaTaskService extends IService<TaTask> {

    List<TaTask> findOrdinaryTask(Pageable pageable);

    List<TaTask> findTutorialTask(Pageable pageable);

    TaTask findByTaskId(Integer taskId);

    void updateTaskState(Integer taskId);
}
