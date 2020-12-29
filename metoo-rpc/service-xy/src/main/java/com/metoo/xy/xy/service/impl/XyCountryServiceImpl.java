package com.metoo.xy.xy.service.impl;

import com.metoo.xy.xy.dao.entity.XyCountry;
import com.metoo.xy.xy.dao.mapper.XyCountryMapper;
import com.metoo.xy.xy.dao.repository.XyCountryRepository;
import com.metoo.xy.xy.service.XyCountryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 国度表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class XyCountryServiceImpl extends ServiceImpl<XyCountryMapper, XyCountry> implements XyCountryService {

    @Autowired
    private XyCountryRepository xyCountryRepository;

    @Override
    public XyCountry findByCountryId(Integer countryId) {
        return xyCountryRepository.findByCountryId(countryId);
    }

    @Override
    public XyCountry findByName(String name) {
        return xyCountryRepository.findByName(name);
    }

    @Override
    public List<XyCountry> findByRaceId(Integer raceId) {
        return xyCountryRepository.findByRaceId(raceId);
    }
}
