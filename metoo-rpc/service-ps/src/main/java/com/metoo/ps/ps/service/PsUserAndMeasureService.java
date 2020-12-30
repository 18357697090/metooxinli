package com.metoo.ps.ps.service;

import com.metoo.ps.ps.dao.entity.PsUserAndMeasure;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户心理测量记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsUserAndMeasureService extends IService<PsUserAndMeasure> {

    void updateMeasure(Integer uid, int scaleId);

    PsUserAndMeasure findByUidAndScaleId(Integer uid, Integer scaleId);

    void updateCount(Integer i, Integer uid, Integer scaleId);
}
