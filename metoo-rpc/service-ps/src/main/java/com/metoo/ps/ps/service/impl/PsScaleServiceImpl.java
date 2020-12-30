package com.metoo.ps.ps.service.impl;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.pojo.ps.model.PsScaleModel;
import com.metoo.ps.ps.dao.entity.PsScale;
import com.metoo.ps.ps.dao.mapper.PsScaleMapper;
import com.metoo.ps.ps.dao.repository.PsScaleRepository;
import com.metoo.ps.ps.service.PsScaleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 心理测量量表表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class PsScaleServiceImpl extends ServiceImpl<PsScaleMapper, PsScale> implements PsScaleService {

    @Resource
    private PsScaleRepository psScaleRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public RE cl(Integer page) {
        Pageable pageable= PageRequest.of(page,7);
        List<PsScale> allBySpare = psScaleRepository.findAllBySpare(1, pageable);
        if(OU.isBlack(allBySpare)){
            return RE.noData();
        }
        return RE.ok(allBySpare);
    }

    @Override
    public List<PsScaleModel> findScaleRand() {
        List<PsScale> scaleRand = psScaleRepository.findScaleRand();
        if(OU.isBlack(scaleRand)){
            return null;
        }
        return scaleRand.stream().flatMap(e->{
            return Stream.of(mapper.map(e, PsScaleModel.class));
        }).collect(Collectors.toList());
    }

    @Override
    public PsScale findByScaleId(Integer scaleId) {
        return psScaleRepository.findByScaleId(scaleId);
    }

    @Override
    public List<PsScale> findByScaleGatherId(Integer clgatherid) {
        return psScaleRepository.findByScaleGatherId(clgatherid);
    }

    @Override
    public void updateNumber(Integer number, Integer scaleId) {
        psScaleRepository.updateNumber(number, scaleId);
    }
}
