package com.metoo.user.ta.api;

import com.metoo.api.ta.TaTaskToUserApi;
import com.metoo.user.ta.service.TaTaskToLevelService;
import com.metoo.user.ta.service.TaTaskToUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 任务大厅表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class TaTaskToToUserApiImpl implements TaTaskToUserApi {

    @Autowired
    private TaTaskToUserService taTaskToUserService;

}
