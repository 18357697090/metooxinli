package com.metoo.user.in.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.in.InSwiperApi;
import com.metoo.pojo.in.vo.InSwiperVo;
import com.metoo.user.in.dao.entity.InSwiper;
import com.metoo.user.in.service.InSwiperService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 首页轮播图 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
public class InSwiperApiImpl implements InSwiperApi {

    @Resource
    private InSwiperService inSwiperService;

    @Override
    public RE swiper() {
        List<InSwiper> list = inSwiperService.list();
        if(OU.isBlack(list)){
            return RE.noData();
        }
        return RE.ok(list);
    }

    @Override
    public RE save(InSwiperVo vo) {
        InSwiper pojo = new InSwiper();
        pojo.setSwiper(vo.getSwiper());
        pojo.setAddress(vo.getAddress());
        inSwiperService.save(pojo);
        return RE.ok();
    }
}
