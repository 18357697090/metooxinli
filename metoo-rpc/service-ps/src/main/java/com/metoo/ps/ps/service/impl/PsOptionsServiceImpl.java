package com.metoo.ps.ps.service.impl;

import com.loongya.core.util.OU;
import com.metoo.pojo.ps.model.PsOptionsModel;
import com.metoo.ps.ps.dao.entity.PsOptions;
import com.metoo.ps.ps.dao.mapper.PsOptionsMapper;
import com.metoo.ps.ps.dao.repository.PsOptionsRepository;
import com.metoo.ps.ps.service.PsOptionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 心理测量题目选项表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class PsOptionsServiceImpl extends ServiceImpl<PsOptionsMapper, PsOptions> implements PsOptionsService {

    @Autowired
    private PsOptionsRepository psOptionsRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public PsOptionsModel findByScaleId(Integer scaleId) {
        PsOptions byScaleId = psOptionsRepository.findByScaleId(scaleId);
        if(OU.isBlack(byScaleId)){
            return null;
        }
        return mapper.map(byScaleId, PsOptionsModel.class);
    }
}
