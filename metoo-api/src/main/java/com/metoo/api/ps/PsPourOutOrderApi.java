package com.metoo.api.ps;

import com.loongya.core.util.RE;
import com.metoo.pojo.ps.vo.PsConsultOrderVo;
import com.metoo.pojo.ps.vo.PsPourOutVo;

/**
 * <p>
 * 心理咨询师表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsPourOutOrderApi {

    RE buyPour(PsPourOutVo vo);
}
