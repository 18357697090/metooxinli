package com.metoo.api.ps;

import com.loongya.core.util.RE;
import com.metoo.pojo.ps.model.PsScaleResultModel;

import java.util.List;

public interface PsScaleResultApi {
    List<PsScaleResultModel> getScaleResult(Integer scaleId);
}
