package com.metoo.api.ps;

import com.metoo.pojo.ps.model.PsScaleOptionsModel;

/**
 * <p>
 * 心理测量题目选项表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsScaleOptionsApi {

    PsScaleOptionsModel findByScaleId(Integer scaleId);


}
