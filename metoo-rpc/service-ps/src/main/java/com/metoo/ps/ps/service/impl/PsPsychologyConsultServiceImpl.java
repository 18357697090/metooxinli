package com.metoo.ps.ps.service.impl;

import com.loongya.core.util.OU;
import com.metoo.api.ps.PsPsychologyConsultApi;
import com.metoo.pojo.ps.model.PsPsychologyConsultModel;
import com.metoo.ps.ps.dao.entity.PsPsychologyConsult;
import com.metoo.ps.ps.dao.mapper.PsPsychologyConsultMapper;
import com.metoo.ps.ps.dao.repository.PsPsychologyConsultRepository;
import com.metoo.ps.ps.service.PsPsychologyConsultService;
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
public class PsPsychologyConsultServiceImpl extends ServiceImpl<PsPsychologyConsultMapper, PsPsychologyConsult> implements PsPsychologyConsultService {

    @Autowired
    private PsPsychologyConsultRepository psPsychologyConsultRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public List<PsPsychologyConsult> findByOnLine(Integer i, Pageable pageable) {
        return psPsychologyConsultRepository.findByOnLine(i, pageable);
    }

    @Override
    public List<PsPsychologyConsultModel> findPsychologyConsultRand() {
        List<PsPsychologyConsult> psPsychologyConsultRand = psPsychologyConsultRepository.findPsPsychologyConsultRand();
        if(OU.isBlack(psPsychologyConsultRand)){
            return null;
        }
        return psPsychologyConsultRand.stream().flatMap(e->{
            return Stream.of(mapper.map(e, PsPsychologyConsultModel.class));
        }).collect(Collectors.toList());
    }
}
