package com.metoo.api.in;

import com.loongya.core.util.RE;
import com.metoo.pojo.in.vo.InAreaVo;

/**
 * <p>
 * 首页轮播图 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface InBannerApi {

    RE inBannerList(Integer type);
}
