package com.metoo.web.controller.ps;


import com.loongya.core.util.RE;
import com.metoo.api.ps.PsArticleBannerApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 心理文章轮播图表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/psArticleBanner")
public class PsArticleBannerController {

    @DubboReference
    private PsArticleBannerApi psArticleBannerApi;

    /**
     * bannerList接口
     * @return
     */
    @PostMapping("/getBannerList")
    public RE getBannerList(){
        return psArticleBannerApi.getBannerList();
    }

}
