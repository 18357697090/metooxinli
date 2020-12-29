package com.metoo.user.tj.api;

import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.pojo.tj.model.TjUserAccountModel;
import com.metoo.user.tj.dao.entity.TjUserAccount;
import com.metoo.user.tj.service.TjUserAccountService;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * <p>
 * 用户账户表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
public class TjUserAccountApiImpl implements TjUserAccountApi {

    @Autowired
    private TjUserAccountService tjUserAccountService;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Override
    public TjUserAccountModel findByUid(Integer uid) {
        TjUserAccount pojo = tjUserAccountService.findByUid(uid);
        TjUserAccountModel model = dozerBeanMapper.map(pojo, TjUserAccountModel.class);
        return model;
    }

    @Override
    public void updateBalance(BigDecimal subtract, Integer uid) {
        tjUserAccountService.updateBalance(subtract, uid);
    }
}
