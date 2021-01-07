package com.metoo.ps.ps.service.impl;

import com.loongya.core.util.OU;
import com.metoo.api.ps.PsConsultApi;
import com.metoo.pojo.ps.model.PsConsultModel;
import com.metoo.ps.ps.dao.entity.PsConsult;
import com.metoo.ps.ps.dao.mapper.PsConsultMapper;
import com.metoo.ps.ps.dao.repository.PsConsultRepository;
import com.metoo.ps.ps.service.PsConsultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 心理咨询师表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class PsConsultServiceImpl extends ServiceImpl<PsConsultMapper, PsConsult> implements PsConsultService {

    @Autowired
    private PsConsultRepository psConsultRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public List<PsConsult> findByOnLine(Integer i, Pageable pageable) {
        return psConsultRepository.findByOnLine(i, pageable);
    }

    @Override
    public List<PsConsultModel> findPsychologyConsultRand() {
        List<PsConsult> psConsultRand = psConsultRepository.findPsConsultRand();
        if(OU.isBlack(psConsultRand)){
            return null;
        }
        return psConsultRand.stream().flatMap(e->{
            return Stream.of(mapper.map(e, PsConsultModel.class));
        }).collect(Collectors.toList());
    }
}
