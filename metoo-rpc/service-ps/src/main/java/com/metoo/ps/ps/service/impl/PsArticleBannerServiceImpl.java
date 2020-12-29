package com.metoo.ps.ps.service.impl;

import com.loongya.core.util.OU;
import com.metoo.pojo.ps.model.PsArticleBannerModel;
import com.metoo.ps.ps.dao.entity.PsArticleBanner;
import com.metoo.ps.ps.dao.mapper.PsArticleBannerMapper;
import com.metoo.ps.ps.service.PsArticleBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 心理文章轮播图表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class PsArticleBannerServiceImpl extends ServiceImpl<PsArticleBannerMapper, PsArticleBanner> implements PsArticleBannerService {

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public List<PsArticleBannerModel> findAll() {
        List<PsArticleBanner> list = this.list();
        if(OU.isBlack(list)){
            return null;
        }
        return list.stream().flatMap(e->{
            return Stream.of(mapper.map(e, PsArticleBannerModel.class));
        }).collect(Collectors.toList());
    }
}
