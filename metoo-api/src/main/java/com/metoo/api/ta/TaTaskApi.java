package com.metoo.api.ta;

import com.loongya.core.util.RE;
import com.metoo.pojo.old.vo.PublishTaskDTO;

/**
 * <p>
 * 任务大厅表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface TaTaskApi {

    RE taskList(Integer page);

    RE tutorialTaskList(Integer page);

    RE publishTask(PublishTaskDTO publishTaskDTO, Integer uid);

    RE acceptTask(Integer uid, Integer taskId);

    RE acceptSubmitTask(Integer uid, Integer taskId);
}
