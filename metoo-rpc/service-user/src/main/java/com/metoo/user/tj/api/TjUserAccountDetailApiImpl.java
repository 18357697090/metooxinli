package com.metoo.user.tj.api;

import com.metoo.api.tj.TjUserAccountDetailApi;
import com.metoo.pojo.tj.model.TjUserAccountDetailModel;
import com.metoo.user.tj.dao.entity.TjUserAccountDetail;
import com.metoo.user.tj.service.TjUserAccountDetailService;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class TjUserAccountDetailApiImpl implements TjUserAccountDetailApi {

    @Autowired
    private TjUserAccountDetailService tjUserAccountDetailService;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public void save(TjUserAccountDetailModel zhRecord) {
        TjUserAccountDetail pojo = mapper.map(zhRecord, TjUserAccountDetail.class);
        tjUserAccountDetailService.save(pojo);
    }
}
