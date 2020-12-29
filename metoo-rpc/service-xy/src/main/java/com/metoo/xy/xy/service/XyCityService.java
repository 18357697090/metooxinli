package com.metoo.xy.xy.service;

import com.metoo.xy.xy.dao.entity.XyCity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 城表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface XyCityService extends IService<XyCity> {

    List<XyCity> findByCountryId(Integer countryId);

    XyCity findByCityId(int countryId);
}
