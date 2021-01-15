package com.metoo.wechat.controller.login;

import com.loongya.core.util.AssertUtils;
import com.loongya.core.util.RE;
import com.metoo.api.wechat.login.WechatLoginApi;
import com.metoo.pojo.login.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private WechatLoginApi wechatLoginApi;

    @RequestMapping(value = "/register")
    public RE register(LoginVo vo) {
        AssertUtils.checkParam(vo.getType(), vo.getJsCode(), vo.getOpenId());
        if(vo.getType() == 1){
            AssertUtils.checkParam(vo.getUsername(), vo.getPassword());
        }
        return wechatLoginApi.register(vo);
    }
}
