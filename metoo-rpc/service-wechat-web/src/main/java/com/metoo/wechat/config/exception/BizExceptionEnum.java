package com.metoo.wechat.config.exception;


import com.loongya.core.util.CommEnum;
import com.loongya.core.util.CommsEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 所有业务异常的枚举
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午5:04:51
 */
public enum BizExceptionEnum implements CommEnum {

    /**
     * token异常
     */
    TOKEN_EXPIRED("700", "token过期"),
    TOKEN_ERROR("700", "token验证失败"),

    /**
     * 签名异常
     */
    SIGN_ERROR("700", "签名验证失败"),

    /**
     * 其他
     */
    AUTH_REQUEST_ERROR("400", "账号密码错误"),
    SUCCESS("200", "成功!"),
    BODY_NOT_MATCH("400","请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH("401","请求的数字签名不匹配!"),
    NOT_FOUND("404", "未找到该资源!"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVER_BUSY("503","服务器正忙，请稍后再试!");

    BizExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;

    private String msg;


    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
