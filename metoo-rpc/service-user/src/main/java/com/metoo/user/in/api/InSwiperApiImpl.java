package com.metoo.user.in.api;

import com.loongya.core.util.CopyUtils;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.loongya.core.util.aliyun.OSSUtil;
import com.metoo.api.in.InSwiperApi;
import com.metoo.pojo.in.model.InSwiperModel;
import com.metoo.pojo.in.vo.InSwiperVo;
import com.metoo.user.in.dao.entity.InSwiper;
import com.metoo.user.in.service.InSwiperService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
@Transactional
public class InSwiperApiImpl implements InSwiperApi {

    @Resource
    private InSwiperService inSwiperService;

    @Override
    public RE indexBannerList() {
        List<InSwiper> list = inSwiperService.list();
        if(OU.isBlack(list)){
            return RE.noData();
        }
        return RE.ok(list.stream().flatMap(e->{
            InSwiperModel model = CopyUtils.copy(e, new InSwiperModel());
            model.setSwiper(OSSUtil.fillPath(model.getSwiper()));
            return Stream.of(model);
        }).collect(Collectors.toList()));
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
