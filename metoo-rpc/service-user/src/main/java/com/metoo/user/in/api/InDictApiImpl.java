package com.metoo.user.in.api;

import com.metoo.api.in.InDictApi;
import com.metoo.user.in.service.InDictService;
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
public class InDictApiImpl implements InDictApi {

    @Resource
    private InDictService inDictService;



}
