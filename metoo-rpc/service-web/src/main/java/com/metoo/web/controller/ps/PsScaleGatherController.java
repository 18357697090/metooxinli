package com.metoo.web.controller.ps;


import com.loongya.core.util.AssertUtils;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsScaleApi;
import com.metoo.api.ps.PsScaleGatherApi;
import com.metoo.pojo.ps.vo.PsScaleVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 心理测量量表集合表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/psScaleGather")
public class PsScaleGatherController {

    @DubboReference
    private PsScaleGatherApi psScaleGatherApi;

    @DubboReference
    private PsScaleApi psScaleApi;

    /**
     * 推荐测量-测量列表
     * @return
     */
    @PostMapping("/findPsScaleListbyclgatherid")
    @ApiOperation("推荐测量集合内容")
    public RE findPsScaleListbyclgatherid(PsScaleVo vo){
        RE re = AssertUtils.checkParam(vo.getClgatherId());
        if(re.isFail()){
            return re;
        }
        return psScaleApi.findPsScaleListbyclgatherid(vo);
    }

}
