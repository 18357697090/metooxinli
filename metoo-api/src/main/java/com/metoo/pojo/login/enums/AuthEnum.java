package com.metoo.pojo.login.enums;

import com.loongya.core.util.CommEnum;
import lombok.Getter;

public enum AuthEnum implements CommEnum {

    /** 成功 */
    LOGIN_TIMEOUT("-1", "登录超时，请重新登录!"),
   ;

    private String code;
    private String msg;

    AuthEnum() {
    }

    AuthEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
