package com.loongya.core.util;

import java.util.List;
import java.util.Optional;

public class OU<M> {

    /**
     * 有一个为空,为true
     * @param ms
     * @param <M>
     * @return
     */
    public static<M> boolean hasAnyBlack(M ...ms){
        for(M m: ms){
            if(isBlack(m)){
                return true;
            }
        }
        return false;
    }

    /**
     * 所有都不为空
     * @param ms
     * @param <M>
     * @return
     */
    public static<M> boolean hasAllNotBlack(M ...ms){
        for(M m: ms){
            if(isBlack(m)){
                return false;
            }
        }
        return true;
    }
    public static<M> boolean isBlack(M m){
        if(m instanceof String){
            if(!Optional.ofNullable(m).isPresent() || ((String) m).trim().length() ==0){
                return true;
            }
            return false;
        }
        if(m instanceof List){
            if(!Optional.ofNullable(m).isPresent() || ((List) m).size() == 0){
                return true;
            }
            return false;
        }
        if(!Optional.ofNullable(m).isPresent()){
            return true;
        }
        return false;
    }
    public static<M> boolean isNotBlack(M m){
        if(m instanceof String){
            if(Optional.ofNullable(m).isPresent() && ((String) m).trim().length() >0){
                return true;
            }else {
                return false;
            }
        }
        if(m instanceof List){
            if(Optional.ofNullable(m).isPresent() && ((List) m).size() >0){
                return true;
            }else {
                return false;
            }
        }
        if(Optional.ofNullable(m).isPresent()){
            return true;
        }
        return false;
    }
}
