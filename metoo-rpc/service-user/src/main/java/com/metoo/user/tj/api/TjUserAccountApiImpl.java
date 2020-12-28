package com.metoo.user.tj.api;

import com.loongya.core.util.RE;
import com.metoo.api.tj.TjUserAccountApi;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;


@Component
@DubboService
public class TjUserAccountApiImpl implements TjUserAccountApi {

//    @Resource
//    private TjUserAccountService tjUserAccountService;


    @Override
    public RE getUserAccountByUserId(Integer userId) {

//        return tjUserAccountService.getUserAccountByUserId(userId);
        return null;
    }
}
