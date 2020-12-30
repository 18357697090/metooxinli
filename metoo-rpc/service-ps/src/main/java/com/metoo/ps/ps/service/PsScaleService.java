package com.metoo.ps.ps.service;

import com.loongya.core.util.RE;
import com.metoo.pojo.ps.model.PsScaleModel;
import com.metoo.ps.ps.dao.entity.PsScale;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 心理测量量表表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsScaleService extends IService<PsScale> {

    RE cl(Integer page);

    List<PsScaleModel> findScaleRand();

    PsScale findByScaleId(Integer scaleId);

    List<PsScale> findByScaleGatherId(Integer clgatherid);

    void updateNumber(Integer number, Integer scaleId);
}
