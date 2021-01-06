package com.metoo.api.ps;

import com.loongya.core.util.RE;
import com.metoo.pojo.ps.model.PsScaleProblemModel;

import java.util.List;

/**
 * <p>
 * 心理测量量表题目表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsScaleProblemApi {

    RE problem(Integer scaleId);

    List<PsScaleProblemModel> findByScaleId(Integer scaleId);
}
