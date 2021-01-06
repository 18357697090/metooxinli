package com.metoo.web.controller.ps;


import com.loongya.core.util.RE;
import com.metoo.api.ps.PsScaleApi;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 心理测量量表表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/psScale")
public class PsScaleController {

    @DubboReference
    private PsScaleApi psScaleApi;

    @ApiOperation("精品测量")
    @GetMapping("/cl")
    public RE cl(Integer page){
        return psScaleApi.cl(page);
    }

    @GetMapping("/index")
    public RE index(){
        return psScaleApi.index();
    }


}
