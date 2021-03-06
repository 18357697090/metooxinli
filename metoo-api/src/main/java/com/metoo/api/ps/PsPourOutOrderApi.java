package com.metoo.api.ps;

import com.loongya.core.util.RE;
import com.metoo.pojo.ps.vo.PsConsultOrderVo;
import com.metoo.pojo.ps.vo.PsConsultVo;
import com.metoo.pojo.ps.vo.PsPourOutVo;

/**
 * <p>
 * 倾诉师表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsPourOutOrderApi {

    RE buyPour(PsPourOutVo vo);

    RE getPourOrderList(PsPourOutVo vo);

    String UnfinishedConsult(PsConsultVo vo);
}
