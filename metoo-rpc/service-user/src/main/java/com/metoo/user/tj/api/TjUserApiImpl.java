package com.metoo.user.tj.api;

import com.metoo.api.tj.TjUserApi;
import com.metoo.user.tj.dao.entity.TjUser;
import com.metoo.user.tj.service.TjUserService;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@DubboService
public class TjUserApiImpl implements TjUserApi {

    @Resource
    private TjUserService tjUserService;

    @Override
    public RE getUserById(Integer id) {
        TjUser tjUser = tjUserService.getTjUserById(id);
        if(OU.isBlack(tjUser)){
            return RE.serviceFail("失败");
        }
        return RE.ok(tjUser);
    }

    @Override
    public RE getList() {
        return tjUserService.getList();
    }
}
