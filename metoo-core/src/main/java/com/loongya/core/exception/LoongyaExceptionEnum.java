package com.loongya.core.exception;

import com.loongya.core.util.CommEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Guns异常枚举
 *
 * @author fengshuonan
 * @Date 2017/12/28 下午10:33
 */
@Getter
@NoArgsConstructor
public enum LoongyaExceptionEnum implements CommEnum {

    /**
     * 其他
     */
    INVLIDE_DATE_STRING("400", "输入日期格式不对"),

    /**
     * 其他
     */
    WRITE_ERROR("500", "渲染界面错误"),

    /**
     * 文件上传
     */
    FILE_READING_ERROR("400", "FILE_READING_ERROR!"),
    FILE_NOT_FOUND("400", "FILE_NOT_FOUND!"),

    /**
     * 错误的请求
     */
    REQUEST_NULL("400", "请求有错误"),
    SERVER_ERROR("500", "服务器异常")
    ;

    LoongyaExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;

    private String msg;


}
