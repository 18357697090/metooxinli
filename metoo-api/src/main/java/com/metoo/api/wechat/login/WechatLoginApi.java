package com.metoo.api.wechat.login;

import com.loongya.core.util.RE;
import com.metoo.pojo.login.vo.LoginVo;

public interface WechatLoginApi {

    RE login(LoginVo vo);
}
