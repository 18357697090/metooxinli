package com.metoo.api.ps;

import com.loongya.core.util.RE;
import com.metoo.pojo.ps.vo.PsConsultVo;
import com.metoo.pojo.ps.vo.PsPourOutVo;

/**
 * <p>
 * 心理倾诉师表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsPourOutApi {

    RE getPourList(PsPourOutVo vo);

    RE getPourDetail(PsPourOutVo vo);

}
