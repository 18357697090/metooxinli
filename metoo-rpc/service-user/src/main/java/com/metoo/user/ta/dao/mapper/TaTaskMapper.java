package com.metoo.user.ta.dao.mapper;

import com.metoo.pojo.ta.model.TaTaskModel;
import com.metoo.pojo.ta.vo.TaTaskVo;
import com.metoo.user.ta.dao.entity.TaTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 任务大厅表 Mapper 接口
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface TaTaskMapper extends BaseMapper<TaTask> {

    List<TaTaskModel> taskList(TaTaskVo vo);
}
