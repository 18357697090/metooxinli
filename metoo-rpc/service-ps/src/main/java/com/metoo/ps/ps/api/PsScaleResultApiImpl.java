package com.metoo.ps.ps.api;

import com.metoo.api.ps.PsScaleResultApi;
import com.metoo.pojo.ps.model.PsScaleResultModel;
import com.metoo.ps.ps.service.PsScaleResultService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@DubboService
@Transactional
public class PsScaleResultApiImpl implements PsScaleResultApi {

    @Autowired
    private PsScaleResultService psScaleResultService;

    @Override
    public List<PsScaleResultModel> PsScaleResultModel(Integer scaleId) {
        return psScaleResultService.PsScaleResultModel(scaleId);
    }
}
