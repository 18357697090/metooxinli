package com.metoo.web.controller.in;


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
@RequestMapping("/in/in-swiper")
public class InSwiperController {

    //轮播图
    @RequestMapping("/swiper")
    public List<Swiper> swiper(){
        return swiperDao.findAll();
    }


    //发布轮播图
    @RequestMapping("/jhxksave")
    public void save(@RequestBody Swiper swiper){
        System.out.println(swiper);
        swiperDao.save(swiper);
    }


}
