package com.metoo.api.ta;

import com.loongya.core.util.RE;
import com.metoo.pojo.old.vo.PublishTaskDTO;
import com.metoo.pojo.ta.vo.TaTaskVo;

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

    RE tutorialTaskList(Integer page);

    RE acceptTask(Integer uid, Integer taskId);

    RE acceptSubmitTask(Integer uid, Integer taskId);

    RE publishTask(TaTaskVo vo);
}
