package com.metoo.api.ps;

import com.loongya.core.util.RE;

/**
 * <p>
 * 心理测量量表题目表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsProblemApi {

    RE problem(Integer scaleId);
}
