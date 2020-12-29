package com.metoo.ps.ps.service;

import com.metoo.pojo.ps.model.PsArticleBannerModel;
import com.metoo.ps.ps.dao.entity.PsArticleBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 心理文章轮播图表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsArticleBannerService extends IService<PsArticleBanner> {

    List<PsArticleBannerModel> findAll();
}
