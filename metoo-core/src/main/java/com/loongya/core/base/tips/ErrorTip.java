package com.loongya.core.base.tips;


import lombok.extern.slf4j.Slf4j;

/**
 * 返回给前台的错误提示
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午5:05:22
 */
@Slf4j
public class ErrorTip extends Tip {

    public ErrorTip(int code, String msg) {
        super();
        if(code == 700){
            this.code = code;
            this.msg = msg;
        }else{
            log.error("系统出现异常，异常Code={}，异常信息={}",code,msg);
            this.code = 999;
            this.msg = "系统出现异常，请联系管理员";
        }
    }
}
