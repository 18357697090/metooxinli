package com.metoo.user.tj.api;

import com.loongya.core.util.RE;
import com.metoo.api.tj.TjUserApi;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;


@Component
@DubboService
public class TjUserApiImpl implements TjUserApi {

//    @Resource
//    private TjUserService tjUserService;

    @Override
    public RE getUserById(Integer id) {
//        TjUser tjUser = tjUserService.getTjUserById(id);
//        if(OU.isBlack(tjUser)){
//            return RE.serviceFail("失败");
//        }
//        return RE.ok(tjUser);
        return RE.ok();
    }

    @Override
    public RE getList() {
//        return tjUserService.getList();
        return RE.ok();
    }
}
