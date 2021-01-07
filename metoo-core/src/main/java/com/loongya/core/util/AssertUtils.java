package com.loongya.core.util;


import com.loongya.core.exception.LoongyaException;

public class AssertUtils {

    public static boolean success(RE t){
        if(t.getCode().equals("0")){
            return true;
        }else {
            return false;
        }
    }
    public static boolean fail(RE t){

        if(t.getCode().equals("0")){
            return false;
        }else {
            return true;
        }
    }

    public static<M> RE checkParam(M ...ms) {
        if(OU.hasAnyBlack(ms)){
            throw new LoongyaException(CommsEnum.PARAM_ERR);
        }
        return RE.ok();
    }
}
