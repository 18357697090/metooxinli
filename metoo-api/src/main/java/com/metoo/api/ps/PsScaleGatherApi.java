package com.metoo.api.ps;

import com.loongya.core.util.RE;
import com.metoo.pojo.ps.vo.PsScaleVo;

/**
 * <p>
 * 心理测量量表集合表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsScaleGatherApi {

    RE getClgatherWithReCommend(PsScaleVo vo);

    RE getClgatherWithReCommendMore();
}
