package com.metoo.xy.xy.service;

import com.metoo.xy.xy.dao.entity.XyCountry;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 国度表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface XyCountryService extends IService<XyCountry> {

    XyCountry findByCountryId(Integer countryId);

    XyCountry findByName(String name);

}
