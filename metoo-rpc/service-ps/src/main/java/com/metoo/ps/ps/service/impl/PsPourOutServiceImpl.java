package com.metoo.ps.ps.service.impl;

import com.loongya.core.util.OU;
import com.metoo.pojo.ps.model.PsPourOutModel;
import com.metoo.ps.ps.dao.entity.PsPourOut;
import com.metoo.ps.ps.dao.mapper.PsPourOutMapper;
import com.metoo.ps.ps.dao.repository.PsPourOutRepository;
import com.metoo.ps.ps.service.PsPourOutService;
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
 * 心理倾诉师表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class PsPourOutServiceImpl extends ServiceImpl<PsPourOutMapper, PsPourOut> implements PsPourOutService {

    @Autowired
    private PsPourOutRepository psPourOutRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public List<PsPourOutModel> findByOnLine(Integer i, Pageable pageable) {
        List<PsPourOut> byOnLine = psPourOutRepository.findByOnLine(i, pageable);
        if(OU.isBlack(byOnLine)){
            return null;
        }
        return byOnLine.stream().flatMap(e->{
            return Stream.of(mapper.map(e, PsPourOutModel.class));
        }).collect(Collectors.toList());
    }

    @Override
    public List<PsPourOutModel> findPourOutRand() {
        List<PsPourOut> pourOutRand = psPourOutRepository.findPourOutRand();
        if(OU.isBlack(pourOutRand)){
            return null;
        }
        return pourOutRand.stream().flatMap(e->{
            return Stream.of(mapper.map(e, PsPourOutModel.class));
        }).collect(Collectors.toList());
    }

}
