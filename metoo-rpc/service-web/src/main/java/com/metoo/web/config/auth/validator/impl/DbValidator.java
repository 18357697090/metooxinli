package com.metoo.web.config.auth.validator.impl;

import com.loongya.core.util.RE;
import com.metoo.api.tj.TjUserApi;
import com.metoo.pojo.login.vo.LoginVo;
import com.metoo.web.config.auth.validator.IReqValidator;
import com.metoo.web.config.auth.validator.dto.Credence;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账号密码验证
 *
 * @author fengshuonan
 * @date 2017-08-23 12:34
 */
@Service
public class DbValidator implements IReqValidator {

    @DubboReference
    TjUserApi tjUserApi;

    @Override
    public boolean validate(Credence credence) {
        LoginVo vo  = new LoginVo();
        vo.setUsername(credence.getCredenceName());
        vo.setPassword(credence.getCredenceCode());
        RE result = tjUserApi.logIn(vo);
        if (result.isFail()) {
            return false;
        } else {
            return true;
        }
    }

}
