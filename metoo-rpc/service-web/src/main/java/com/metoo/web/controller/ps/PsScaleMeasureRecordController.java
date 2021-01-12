package com.metoo.web.controller.ps;


import com.alibaba.fastjson.JSONArray;
import com.loongya.core.util.AssertUtils;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsScaleMeasureRecordApi;
import com.metoo.pojo.login.enums.AuthEnum;
import com.metoo.pojo.ps.model.PsScaleMeasureRecordModel;
import com.metoo.pojo.ps.vo.PsScaleMeasureRecordVo;
import com.metoo.web.config.auth.ThreadLocal;
import com.metoo.web.config.tools.CalculateTheScore;
import com.metoo.web.config.tools.OptionsResult;
import com.metoo.web.config.tools.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户心理测量量表记录表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/psScaleMeasureRecord")
public class PsScaleMeasureRecordController {

    @DubboReference
    private PsScaleMeasureRecordApi psScaleMeasureRecordApi;


    /**
     * 未知 todo.
     * @param time
     * @return
     */
    @PostMapping("/measureRecord")
    public RE measureRecord(String time){
        Integer userId = ThreadLocal.getUserId();
        if(OU.isBlack(userId)){
            return RE.fail(AuthEnum.LOGIN_TIMEOUT);
        }
        return psScaleMeasureRecordApi.measureRecord(userId, time);
    }


    //返回测量分数
    @ApiOperation("测量完成后的返回结果")
    @PostMapping("/result")
    public RE result(Result result){
        Integer userId = ThreadLocal.getUserId();
        if(OU.isBlack(userId)){
            return RE.fail(AuthEnum.LOGIN_TIMEOUT);
        }
        AssertUtils.checkParam(result.getResultStr(), result.getScaleId());
        List<OptionsResult> results = JSONArray.parseArray(result.getResultStr(), OptionsResult.class);
        result.setResults(results);
        psScaleMeasureRecordApi.updateMeasure(userId,result.getScaleId());
        PsScaleMeasureRecordModel model = psScaleMeasureRecordApi.findByUserIdAndScaleId(userId, result.getScaleId());
        String achievement= CalculateTheScore.calculate(result); //todo.
        model.setContent(achievement);
        psScaleMeasureRecordApi.updateRecord(model);
        return RE.ok(model);
    }

}
