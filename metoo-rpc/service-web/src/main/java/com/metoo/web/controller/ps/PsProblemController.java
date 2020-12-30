package com.metoo.web.controller.ps;


import com.loongya.core.util.RE;
import com.metoo.api.ps.PsProblemApi;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
public class PsProblemController {

    @DubboReference
    private PsProblemApi psProblemApi;

    //测量的题目
    @ApiOperation("量表题目")
    @GetMapping("/problem")
    public RE problem(Integer scaleId){
        return psProblemApi.problem(scaleId);
    }

}
