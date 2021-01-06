package com.metoo.ps.ps.service.impl;

import com.loongya.core.util.OU;
import com.metoo.pojo.ps.model.PsScaleProblemModel;
import com.metoo.ps.ps.dao.entity.PsScaleProblem;
import com.metoo.ps.ps.dao.mapper.PsScaleProblemMapper;
import com.metoo.ps.ps.dao.repository.PsScaleProblemRepository;
import com.metoo.ps.ps.service.PsScaleProblemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 心理测量量表题目表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class PsScaleProblemServiceImpl extends ServiceImpl<PsScaleProblemMapper, PsScaleProblem> implements PsScaleProblemService {

    @Autowired
    private PsScaleProblemRepository psProblemRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public List<PsScaleProblemModel> findByScaleId(Integer scaleId) {
        List<PsScaleProblem> byScaleId = psProblemRepository.findByScaleId(scaleId);
        if(OU.isBlack(byScaleId)){
            return null;
        }
        return byScaleId.stream().flatMap(e->{
            return Stream.of(mapper.map(e, PsScaleProblemModel.class));
        }).collect(Collectors.toList());
    }
}
