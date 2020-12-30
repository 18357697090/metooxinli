package com.metoo.api.ps;

import com.loongya.core.util.RE;

/**
 * <p>
 * 心理测量量表详情表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsScaleDetailApi {

    RE gaugedetails(Integer scaleId, Integer uid);
}
