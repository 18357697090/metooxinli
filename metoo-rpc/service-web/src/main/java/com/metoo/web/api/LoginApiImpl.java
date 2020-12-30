package com.metoo.web.api;


import com.loongya.core.util.RE;
import com.metoo.api.login.LoginApi;
import com.metoo.api.tj.TjUserApi;
import com.metoo.pojo.login.vo.LoginVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component
public class LoginApiImpl implements LoginApi {

    @DubboReference
    private TjUserApi tjUserApi;

    @Override
    public RE login(LoginVo loginVo) {
        return tjUserApi.logIn(loginVo);
    }
}
