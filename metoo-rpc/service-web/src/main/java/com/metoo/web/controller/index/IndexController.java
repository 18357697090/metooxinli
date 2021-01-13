package com.metoo.web.controller.index;


import com.loongya.core.util.RE;
import com.metoo.api.in.InSwiperApi;
import com.metoo.api.ps.PsScaleApi;
import com.metoo.pojo.in.vo.InSwiperVo;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 首页轮播图 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @DubboReference
    private InSwiperApi inSwiperApi;

    @DubboReference
    private PsScaleApi psScaleApi;

    /**
     * 首页轮播图
     */
    @RequestMapping("/indexBannerList")
    public RE indexBannerList(){
        return inSwiperApi.indexBannerList();
    }

    /**
     *  推荐测量
     */
    @PostMapping("/indexScaleList")
    @ApiOperation("更多推荐测量")
    public RE indexScaleList(){
        return psScaleApi.indexScaleList();
    }


}
