package com.metoo.ps.ps.service.impl;

import com.loongya.core.util.OU;
import com.metoo.pojo.ps.model.PsScaleOptionsModel;
import com.metoo.ps.ps.dao.entity.PsScaleOptions;
import com.metoo.ps.ps.dao.mapper.PsScaleOptionsMapper;
import com.metoo.ps.ps.dao.repository.PsScaleOptionsRepository;
import com.metoo.ps.ps.service.PsScaleOptionsService;
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
public class PsScaleOptionsServiceImpl extends ServiceImpl<PsScaleOptionsMapper, PsScaleOptions> implements PsScaleOptionsService {

    @Autowired
    private PsScaleOptionsRepository psScaleOptionsRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public PsScaleOptionsModel findByScaleId(Integer scaleId) {
        PsScaleOptions byScaleId = psScaleOptionsRepository.findByScaleId(scaleId);
        if(OU.isBlack(byScaleId)){
            return null;
        }
        return mapper.map(byScaleId, PsScaleOptionsModel.class);
    }
}
