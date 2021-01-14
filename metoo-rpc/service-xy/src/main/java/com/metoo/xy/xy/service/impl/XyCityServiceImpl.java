package com.metoo.xy.xy.service.impl;

import com.metoo.xy.xy.dao.entity.XyCity;
import com.metoo.xy.xy.dao.mapper.XyCityMapper;
import com.metoo.xy.xy.dao.repository.XyCityRepository;
import com.metoo.xy.xy.service.XyCityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 城表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class XyCityServiceImpl extends ServiceImpl<XyCityMapper, XyCity> implements XyCityService {

    @Autowired
    private XyCityRepository xyCityRepository;

    @Override
    public List<XyCity> findByCountryId(Integer countryId) {
        return xyCityRepository.findByCountryId(countryId);
    }

    @Override
    public XyCity findByCityId(int countryId) {
        return this.getById(countryId);
    }
}
