package com.metoo.ps.ps.service;

import com.metoo.ps.ps.dao.entity.PsScaleMeasureRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户心理测量量表记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsScaleMeasureRecordService extends IService<PsScaleMeasureRecord> {

    List<PsScaleMeasureRecord> findBytime(String time, Integer uid);

    void updateMeasure(Integer uid, int scaleId);

    PsScaleMeasureRecord findByUidAndScaleId(Integer uid, Integer scaleId);

    PsScaleMeasureRecord findFirstByUidAndScaleIdOrderByCreateTimeDesc(Integer uid, Integer scaleId);

    void updateCount(Integer i, Integer uid, Integer scaleId);

}
