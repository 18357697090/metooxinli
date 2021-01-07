package com.metoo.api.ps;

import com.loongya.core.util.RE;
import com.metoo.pojo.ps.vo.PsConsultVo;

/**
 * <p>
 * 心理咨询师表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsConsultApi {

    RE psConsultList(PsConsultVo vo);

    RE psConsultDetail(PsConsultVo vo);

    RE psConsultBannerList(PsConsultVo vo);
}
