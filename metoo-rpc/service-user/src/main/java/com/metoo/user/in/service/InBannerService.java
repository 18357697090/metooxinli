package com.metoo.user.in.service;

import com.metoo.user.in.dao.entity.InBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author loongya
 * @since 2021-01-07
 */
public interface InBannerService extends IService<InBanner> {

    List<InBanner> findAllByType(Integer type);
}
