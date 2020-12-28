package com.metoo.web.controller.ps;


import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/ps/ps-problem")
public class PsProblemController {
    //测量的题目
    @ApiOperation("量表题目")
    @GetMapping("/problem")
    public Problems problem(Integer scaleId){
        Problems problems=new Problems();
        problems.setOptions(optionsDao.findByScaleId(scaleId));
        problems.setProblems(problemDao.findByScaleId(scaleId));
        return problems;
    }

}
