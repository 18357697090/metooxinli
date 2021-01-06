package com.loongya.core.exception;

import com.loongya.core.util.CommEnum;
import com.loongya.core.util.CommsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 封装guns的异常
 *
 * @author fengshuonan
 * @Date 2017/12/28 下午10:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoongyaException extends RuntimeException {

    private String code;

    private String msg;

    public LoongyaException(){super();}

    public LoongyaException(CommEnum commEnum) {
        super(commEnum.getCode());
        this.code = commEnum.getCode();
        this.msg = commEnum.getMsg();
    }

    public LoongyaException(CommEnum commEnum, Throwable cause) {
        super(commEnum.getCode(), cause);
        this.code = commEnum.getCode();
        this.msg = commEnum.getMsg();
    }


    public LoongyaException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public LoongyaException(String code, String msg) {
        super(code);
        this.code = code;
        this.msg = msg;
    }

    public LoongyaException(String code, String msg, Throwable cause) {
        super(code, cause);
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
