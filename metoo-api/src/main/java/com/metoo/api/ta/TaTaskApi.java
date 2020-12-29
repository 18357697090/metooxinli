package com.metoo.api.ta;

import com.loongya.core.util.RE;

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
}
