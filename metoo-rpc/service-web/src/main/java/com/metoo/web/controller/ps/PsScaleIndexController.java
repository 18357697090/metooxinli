package com.metoo.web.controller.ps;


import com.loongya.core.util.CommsEnum;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsScaleApi;
import com.metoo.api.ps.PsScaleGatherApi;
import com.metoo.pojo.login.enums.AuthEnum;
import com.metoo.pojo.ps.vo.PsScaleVo;
import com.metoo.web.config.auth.ThreadLocal;
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
@RequestMapping("/ps/psScaleIndex")
public class PsScaleIndexController {

    @DubboReference
    private PsScaleApi psScaleApi;

    @DubboReference
    private PsScaleGatherApi psScaleGatherApi;

    @GetMapping("/clgather")
    @ApiOperation("推荐测量")
    public RE clgather(PsScaleVo vo){
        Integer userId = ThreadLocal.getUserId();
        if(OU.isBlack(userId)){
            return RE.fail(AuthEnum.LOGIN_TIMEOUT);
        }
        vo.setUserId(userId);
        return psScaleGatherApi.clgather(vo);
    }

    //所有测量集合
    @GetMapping("/clgatherall")
    @ApiOperation("更多推荐测量")
    public RE clgaherall(){
        return psScaleGatherApi.clgaherall();
    }


    @ApiOperation("精品测量")
    @GetMapping("/cl")
    public RE cl(Integer page){
        return psScaleApi.cl(page);
    }


}
