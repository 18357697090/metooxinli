package com.metoo.ps.ps.service.impl;

import com.metoo.ps.ps.dao.entity.PsScaleDetail;
import com.metoo.ps.ps.dao.mapper.PsScaleDetailMapper;
import com.metoo.ps.ps.dao.repository.PsScaleDetailRepository;
import com.metoo.ps.ps.service.PsScaleDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 心理测量量表详情表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class PsScaleDetailServiceImpl extends ServiceImpl<PsScaleDetailMapper, PsScaleDetail> implements PsScaleDetailService {

    @Autowired
    private PsScaleDetailRepository psScaleDetailRepository;

    @Override
    public PsScaleDetail findByScaleId(Integer scaleId) {
        return psScaleDetailRepository.findByScaleId(scaleId);
    }
}
