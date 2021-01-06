package com.loongya.core.util;

import org.springframework.beans.BeanUtils;

public class CopyUtils {

    public static <S,T> T copy(S s,T t){
        BeanUtils.copyProperties(s,t);
        return t;
    }
}
