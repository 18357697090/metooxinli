package com.metoo.web.controller.ps;


import com.metoo.api.ps.PsScaleResultApi;
import com.metoo.pojo.ps.model.PsScaleResultModel;
import com.metoo.pojo.ps.vo.PsScaleResultVo;
import org.apache.dubbo.config.Constants;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author loongya
 * @since 2021-01-20
 */
@RestController
@RequestMapping("/ps/ps-scale-result")
public class PsScaleResultController {

    @DubboReference
    private PsScaleResultApi psScaleResultApi;

    @PostMapping("/getScaleResult")
    public List<PsScaleResultModel> getScaleResult(PsScaleResultVo vo){
        return psScaleResultApi.getScaleResult(vo.getScaleId());
    }

}
