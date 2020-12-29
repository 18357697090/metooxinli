package com.metoo.api.xy;

import com.loongya.core.util.RE;

/**
 * <p>
 * 城表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface XyCityApi {

    RE getCity(Integer uid, Integer countryId);
}
