package com.metoo.wechat.config.auth;

import com.loongya.core.exception.LoongyaException;
import com.loongya.core.util.OU;
import com.metoo.pojo.login.enums.AuthEnum;

public class ThreadLocal {

    // 线程绑定的存储空间
    private static final InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void saveUserId(String userId){
        threadLocal.set(userId);
    }

    public static Integer getUserId(){
        String userIds = threadLocal.get();
        if(OU.isBlack(userIds)){
            throw new LoongyaException(AuthEnum.LOGIN_TIMEOUT);
        }
        return Integer.parseInt(userIds);
    }

}
