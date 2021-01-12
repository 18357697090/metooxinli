package com.metoo.web.controller.ps;


import com.loongya.core.util.RE;
import com.metoo.api.ps.PsScaleProblemApi;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 心理测量量表题目表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/psProblem")
public class PsScaleProblemController {

    @DubboReference
    private PsScaleProblemApi psProblemApi;

    //测量的题目
    @ApiOperation("量表题目")
    @PostMapping("/problem")
    public RE problem(Integer scaleId){
        return psProblemApi.problem(scaleId);
    }

}
