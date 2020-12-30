package com.metoo.api.login;

import com.loongya.core.util.RE;
import com.metoo.pojo.login.vo.LoginVo;

public interface LoginApi {

    RE login(LoginVo loginVo);
}
