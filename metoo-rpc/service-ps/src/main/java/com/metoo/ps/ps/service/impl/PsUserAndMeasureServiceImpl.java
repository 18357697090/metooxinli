package com.metoo.ps.ps.service.impl;

import com.metoo.ps.ps.dao.entity.PsUserAndMeasure;
import com.metoo.ps.ps.dao.mapper.PsUserAndMeasureMapper;
import com.metoo.ps.ps.dao.repository.PsUserAndMeasureRepository;
import com.metoo.ps.ps.service.PsUserAndMeasureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户心理测量记录表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class PsUserAndMeasureServiceImpl extends ServiceImpl<PsUserAndMeasureMapper, PsUserAndMeasure> implements PsUserAndMeasureService {

    @Autowired
    private PsUserAndMeasureRepository psUserAndMeasureRepository;

    @Override
    public void updateMeasure(Integer uid, int scaleId) {
        psUserAndMeasureRepository.updateMeasure(uid, scaleId);
    }
}
