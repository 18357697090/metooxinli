package com.metoo.user.tj.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.api.tj.TjUserApi;
import com.metoo.user.tj.dao.entity.TjUser;
import com.metoo.user.tj.service.TjUserAccountService;
import com.metoo.user.tj.service.TjUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@DubboService
public class TjUserAccountApiImpl implements TjUserAccountApi {

    @Resource
    private TjUserAccountService tjUserAccountService;


    @Override
    public RE getUserAccountByUserId(Integer userId) {

        return tjUserAccountService.getUserAccountByUserId(userId);
    }
}
