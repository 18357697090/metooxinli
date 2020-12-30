package com.metoo.ps.ps.service.impl;

import com.metoo.ps.ps.dao.entity.PsScaleGather;
import com.metoo.ps.ps.dao.mapper.PsScaleGatherMapper;
import com.metoo.ps.ps.dao.repository.PsScaleGatherRepository;
import com.metoo.ps.ps.service.PsScaleGatherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 心理测量量表集合表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class PsScaleGatherServiceImpl extends ServiceImpl<PsScaleGatherMapper, PsScaleGather> implements PsScaleGatherService {

    @Autowired
    private PsScaleGatherRepository psScaleGatherRepository;

    @Override
    public PsScaleGather findByScaleGatherId(Integer dw) {
        return psScaleGatherRepository.findByScaleGatherId(dw);
    }

    @Override
    public List<PsScaleGather> findByScaleGatherIdAll() {
        return psScaleGatherRepository.findByScaleGatherIdAll();
    }
}
