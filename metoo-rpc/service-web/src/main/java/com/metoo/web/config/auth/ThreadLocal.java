package com.metoo.web.config.auth;

import com.loongya.core.util.OU;

public class ThreadLocal {

    // 线程绑定的存储空间
    private static final InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void saveUserId(String userId){
        threadLocal.set(userId);
    }

    public static Integer getUserId(){
        String userIds = threadLocal.get();
        if(OU.isBlack(userIds)){
            return null;
        }
        return Integer.parseInt(userIds);
    }

}
