package com.metoo.ps.ps.service;

import com.metoo.pojo.ps.model.PsScaleOptionsModel;
import com.metoo.ps.ps.dao.entity.PsScaleOptions;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 心理测量题目选项表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsScaleOptionsService extends IService<PsScaleOptions> {

    PsScaleOptionsModel findByScaleId(Integer scaleId);
}
