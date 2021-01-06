package com.metoo.ps.ps.api;

import com.metoo.api.ps.PsScaleOptionsApi;
import com.metoo.pojo.ps.model.PsScaleOptionsModel;
import com.metoo.ps.ps.service.PsScaleOptionsService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 心理测量题目选项表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class PsScaleOptionsApiImpl implements PsScaleOptionsApi {

    @Autowired
    private PsScaleOptionsService psScaleOptionsService;

    @Override
    public PsScaleOptionsModel findByScaleId(Integer scaleId) {
        return psScaleOptionsService.findByScaleId(scaleId);
    }
}
