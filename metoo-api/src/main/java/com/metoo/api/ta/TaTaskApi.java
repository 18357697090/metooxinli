package com.metoo.api.ta;

import com.loongya.core.util.RE;
import com.metoo.pojo.old.vo.PublishTaskDTO;
import com.metoo.pojo.ta.model.MyTaTaskModel;
import com.metoo.pojo.ta.vo.AppealTaskVo;
import com.metoo.pojo.ta.vo.CommitTaTaskVo;
import com.metoo.pojo.ta.vo.MyTaTaskVo;
import com.metoo.pojo.ta.vo.TaTaskVo;

import java.util.List;

/**
 * <p>
 * 任务大厅表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface TaTaskApi {

    RE taskList(TaTaskVo vo);

    RE tutorialTaskList(TaTaskVo vo);

    RE acceptTask(TaTaskVo vo);

    RE acceptSubmitTask(Integer uid, Integer taskId);

    RE publishTask(TaTaskVo vo);

    RE taskDetail(TaTaskVo vo);

    RE myAcceptTaskList(MyTaTaskVo vo);

    RE myPublishTaskList(MyTaTaskVo vo);

    RE myPublishTaskDetail(MyTaTaskVo vo);

    RE commitTask(CommitTaTaskVo vo);

    RE confirmTask(MyTaTaskVo vo);

    RE appealTask(AppealTaskVo vo);

    RE deleteTask(MyTaTaskVo vo);
}
