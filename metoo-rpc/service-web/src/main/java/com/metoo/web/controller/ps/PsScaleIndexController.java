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
import org.springframework.web.bind.annotation.PostMapping;
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

    /**
     * 推荐测量
     * @param vo
     * @return
     */
    @PostMapping("/getClgatherWithReCommend")
    @ApiOperation("推荐测量")
    public RE getClgatherWithReCommend(PsScaleVo vo){
        Integer userId = ThreadLocal.getUserId();
        if(OU.isBlack(userId)){
            return RE.fail(AuthEnum.LOGIN_TIMEOUT);
        }
        vo.setUserId(userId);
        return psScaleGatherApi.getClgatherWithReCommend(vo);
    }

    /**
     *  更多推荐测量
     */
    @PostMapping("/getClgatherWithReCommendMore")
    @ApiOperation("更多推荐测量")
    public RE getClgatherWithReCommendMore(){
        return psScaleGatherApi.getClgatherWithReCommendMore();
    }

    /**
     * 精品测量-分页查询
     * @param vo
     * @return
     */
    @ApiOperation("精品测量")
    @PostMapping("/getBoutiqueClgatherList")
    public RE getBoutiqueClgatherList(PsScaleVo vo){
        return psScaleApi.getBoutiqueClgatherList(vo);
    }

}
