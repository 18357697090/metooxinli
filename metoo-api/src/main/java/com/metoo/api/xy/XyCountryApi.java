package com.metoo.api.xy;

import com.loongya.core.util.RE;
import com.metoo.pojo.old.vo.BuildCountryDTO;
import com.metoo.pojo.xy.vo.XyCountryVo;

/**
 * <p>
 * 国度表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface XyCountryApi {

    RE buildCountry(XyCountryVo vo);

    RE getCountry(Integer uid, Integer raceId);
}
