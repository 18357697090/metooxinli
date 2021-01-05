package com.loongya.core.util;


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
            return RE.fail(CommsEnum.PARAM_ERR);
        }
        return RE.ok();
    }
}
