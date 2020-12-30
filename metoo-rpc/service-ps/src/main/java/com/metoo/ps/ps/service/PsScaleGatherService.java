package com.metoo.ps.ps.service;

import com.metoo.ps.ps.dao.entity.PsScaleGather;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 心理测量量表集合表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsScaleGatherService extends IService<PsScaleGather> {

    PsScaleGather findByScaleGatherId(Integer dw);

    List<PsScaleGather> findByScaleGatherIdAll();
}
