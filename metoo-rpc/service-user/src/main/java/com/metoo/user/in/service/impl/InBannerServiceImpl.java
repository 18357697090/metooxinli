package com.metoo.user.in.service.impl;

import com.metoo.user.in.dao.entity.InBanner;
import com.metoo.user.in.dao.mapper.InBannerMapper;
import com.metoo.user.in.dao.repository.InBannerRepository;
import com.metoo.user.in.service.InBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author loongya
 * @since 2021-01-07
 */
@Service
public class InBannerServiceImpl extends ServiceImpl<InBannerMapper, InBanner> implements InBannerService {

    @Autowired
    private InBannerRepository inBannerRepository;

    @Override
    public List<InBanner> findAllByType(Integer type) {
        return inBannerRepository.findAllByType(type);
    }
}
