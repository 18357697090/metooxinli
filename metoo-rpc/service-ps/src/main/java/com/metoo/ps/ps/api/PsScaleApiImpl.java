package com.metoo.ps.ps.api;

import com.loongya.core.util.RE;
import com.metoo.api.ps.PsScaleApi;
import com.metoo.ps.ps.service.PsScaleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@DubboService
public class PsScaleApiImpl implements PsScaleApi {

    @Autowired
    private PsScaleService psScaleService;

    @Override
    public RE cl(Integer page) {
        return psScaleService.cl(page);
    }
}
