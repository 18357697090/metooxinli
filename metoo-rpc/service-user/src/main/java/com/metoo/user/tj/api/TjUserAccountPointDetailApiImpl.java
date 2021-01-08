package com.metoo.user.tj.api;

import com.metoo.api.tj.TjUserAccountCoinDetailApi;
import com.metoo.api.tj.TjUserAccountPointDetailApi;
import com.metoo.user.tj.service.TjUserAccountCoinDetailService;
import com.metoo.user.tj.service.TjUserAccountPointDetailService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户消费记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class TjUserAccountPointDetailApiImpl implements TjUserAccountPointDetailApi {

    @Autowired
    private TjUserAccountPointDetailService tjUserAccountPointDetailService;


}
