package com.metoo.web.controller.in;


import com.loongya.core.util.RE;
import com.metoo.api.in.InSwiperApi;
import com.metoo.pojo.in.vo.InSwiperVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页轮播图 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/in/inSwiper")
public class InSwiperController {

    @DubboReference
    private InSwiperApi inSwiperApi;

    //轮播图
    @RequestMapping("/swiper")
    public RE swiper(){
        return inSwiperApi.swiper();
    }


    //发布轮播图
    @RequestMapping("/jhxksave")
    public RE save(@RequestBody InSwiperVo vo){
        return inSwiperApi.save(vo);
    }


}
