package com.metoo.business.bu.api;

import com.metoo.api.bu.BuUserListApi;
import com.metoo.business.bu.service.BuUserListService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
public class BuUserListImpl implements BuUserListApi {

    @Resource
    private BuUserListService buUserListService;


}
