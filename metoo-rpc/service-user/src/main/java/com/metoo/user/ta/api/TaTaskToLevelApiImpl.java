package com.metoo.user.ta.api;

import com.metoo.api.ta.TaTaskToLevelApi;
import com.metoo.user.ta.dao.entity.TaTaskToLevel;
import com.metoo.user.ta.service.TaTaskImgService;
import com.metoo.user.ta.service.TaTaskToLevelService;
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
public class TaTaskToLevelApiImpl implements TaTaskToLevelApi {

    @Autowired
    private TaTaskToLevelService taTaskToLevelService;

}
