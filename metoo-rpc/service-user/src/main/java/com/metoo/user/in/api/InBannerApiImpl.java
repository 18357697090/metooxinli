package com.metoo.user.in.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.loongya.core.util.CopyUtils;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.loongya.core.util.aliyun.OSSUtil;
import com.metoo.api.in.InAreaApi;
import com.metoo.api.in.InBannerApi;
import com.metoo.pojo.in.model.InAreaModel;
import com.metoo.pojo.in.model.InBannerModel;
import com.metoo.pojo.in.vo.InAreaVo;
import com.metoo.user.in.dao.entity.InArea;
import com.metoo.user.in.dao.entity.InBanner;
import com.metoo.user.in.service.InAreaService;
import com.metoo.user.in.service.InBannerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
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
@Slf4j
public class InBannerApiImpl implements InBannerApi {

    @Resource
    private InBannerService inBannerService;

    @Override
    public RE inBannerList(Integer type) {
        List<InBanner> bannerList = inBannerService.findAllByType(type);

        return RE.ok(bannerList.stream().flatMap(e->{
            InBannerModel model = CopyUtils.copy(e, new InBannerModel());
            model.setImg(OSSUtil.fillPath(e.getImg()));
            return Stream.of(model);
        }).collect(Collectors.toList()));
    }
}
