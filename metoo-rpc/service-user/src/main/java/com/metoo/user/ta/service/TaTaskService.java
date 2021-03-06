package com.metoo.user.ta.service;

import com.metoo.pojo.ta.model.TaTaskModel;
import com.metoo.pojo.ta.vo.MyTaTaskVo;
import com.metoo.pojo.ta.vo.TaTaskVo;
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

    void updateTaskState(Integer taskId);

    List<TaTaskModel> taskList(TaTaskVo vo);

    List<TaTaskModel> myAcceptTaskList(MyTaTaskVo vo);

    List<TaTaskModel> myPublishTaskList(MyTaTaskVo vo);
}
