package com.metoo.ps.ps.service.impl;

import com.loongya.core.util.OU;
import com.metoo.pojo.ps.model.PsProblemModel;
import com.metoo.ps.ps.dao.entity.PsProblem;
import com.metoo.ps.ps.dao.mapper.PsProblemMapper;
import com.metoo.ps.ps.dao.repository.PsProblemRepository;
import com.metoo.ps.ps.service.PsProblemService;
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
public class PsProblemServiceImpl extends ServiceImpl<PsProblemMapper, PsProblem> implements PsProblemService {

    @Autowired
    private PsProblemRepository psProblemRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public List<PsProblemModel> findByScaleId(Integer scaleId) {
        List<PsProblem> byScaleId = psProblemRepository.findByScaleId(scaleId);
        if(OU.isBlack(byScaleId)){
            return null;
        }
        return byScaleId.stream().flatMap(e->{
            return Stream.of(mapper.map(e, PsProblemModel.class));
        }).collect(Collectors.toList());
    }
}
