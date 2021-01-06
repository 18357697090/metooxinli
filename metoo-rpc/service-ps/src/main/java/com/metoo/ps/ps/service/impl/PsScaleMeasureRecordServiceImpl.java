package com.metoo.ps.ps.service.impl;

import com.metoo.ps.ps.dao.entity.PsScaleMeasureRecord;
import com.metoo.ps.ps.dao.mapper.PsScaleMeasureRecordMapper;
import com.metoo.ps.ps.dao.repository.PsScaleMeasureRecordRepository;
import com.metoo.ps.ps.service.PsScaleMeasureRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户心理测量量表记录表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class PsScaleMeasureRecordServiceImpl extends ServiceImpl<PsScaleMeasureRecordMapper, PsScaleMeasureRecord> implements PsScaleMeasureRecordService {

    @Autowired
    private PsScaleMeasureRecordRepository psScaleMeasureRecordRepository;

    @Override
    public List<PsScaleMeasureRecord> findBytime(String time, Integer uid) {
        return psScaleMeasureRecordRepository.findBytime(time, uid);
    }

    @Override
    public void updateMeasure(Integer uid, int scaleId) {
        psScaleMeasureRecordRepository.updateMeasure(uid, scaleId);
    }

    @Override
    public PsScaleMeasureRecord findByUidAndScaleId(Integer uid, Integer scaleId) {
        return psScaleMeasureRecordRepository.findByUidAndScaleId(uid, scaleId);
    }
    @Override
    public PsScaleMeasureRecord findFirstByUidAndScaleIdOrderByCreateTimeDesc(Integer uid, Integer scaleId) {
        return psScaleMeasureRecordRepository.findFirstByUidAndScaleIdOrderByCreateTimeDesc(uid, scaleId);
    }

    @Override
    public void updateCount(Integer i, Integer uid, Integer scaleId) {
        psScaleMeasureRecordRepository.updateCount(i, uid, scaleId);
    }

}
