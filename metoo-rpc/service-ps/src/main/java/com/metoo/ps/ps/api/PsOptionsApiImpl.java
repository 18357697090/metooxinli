package com.metoo.ps.ps.api;

import com.metoo.api.ps.PsOptionsApi;
import com.metoo.pojo.ps.model.PsOptionsModel;
import com.metoo.ps.ps.dao.entity.PsOptions;
import com.metoo.ps.ps.service.PsOptionsService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 心理测量题目选项表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public class PsOptionsApiImpl implements PsOptionsApi {

    @Autowired
    private PsOptionsService psOptionsService;

    @Override
    public PsOptionsModel findByScaleId(Integer scaleId) {
        return psOptionsService.findByScaleId(scaleId);
    }
}
