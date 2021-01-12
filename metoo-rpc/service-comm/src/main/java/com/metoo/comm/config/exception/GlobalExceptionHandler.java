package com.metoo.comm.config.exception;

import com.loongya.core.exception.LoongyaException;
import com.loongya.core.exception.LoongyaExceptionEnum;
import com.loongya.core.util.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = LoongyaException.class)
    @ResponseBody
    public RE bizExceptionHandler(HttpServletRequest req, LoongyaException e){
        log.error("发生业务异常！原因是：{}",e.getMsg());
        return RE.fail(e.getCode(),e.getMsg());
    }


    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public RE exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常！原因是:",e);
        return RE.fail(LoongyaExceptionEnum.SERVER_ERROR.getCode(), e.getMessage());
    }


    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public RE exceptionHandler(HttpServletRequest req, Exception e){
        log.error("未知异常！原因是:",e);
        log.error(e.getMessage());
        return RE.fail(LoongyaExceptionEnum.SERVER_ERROR.getCode(), e.getMessage());
    }
}
