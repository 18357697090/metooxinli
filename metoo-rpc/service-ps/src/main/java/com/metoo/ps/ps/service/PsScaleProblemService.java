package com.metoo.ps.ps.service;

import com.metoo.pojo.ps.model.PsScaleProblemModel;
import com.metoo.ps.ps.dao.entity.PsScaleProblem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 心理测量量表题目表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsScaleProblemService extends IService<PsScaleProblem> {

    List<PsScaleProblemModel> findByScaleId(Integer scaleId);
}
