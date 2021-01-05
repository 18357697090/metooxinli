package com.loongya.core.util;

import lombok.Getter;

@Getter
public enum CommsEnum implements CommEnum {

    /** 成功 */
    SUCCESS("0", "操作成功!"),
    NO_DATA("605", "没有数据"),
    SAVE_FAIL("1", "保存失败!"),
    PARAM_ERR("1", "缺少参数"),
   ;

    private String code;
    private String msg;

    CommsEnum() {
    }

    CommsEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
