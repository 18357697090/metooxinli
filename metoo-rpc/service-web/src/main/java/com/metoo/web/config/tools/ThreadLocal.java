package com.metoo.web.config.tools;

import org.springframework.security.core.context.SecurityContextHolder;

public class ThreadLocal {
    public static Integer getUserId() {
        try {
            return Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        }catch (Exception e){
            return null;
        }
    }
}
