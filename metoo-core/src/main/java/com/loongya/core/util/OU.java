package com.loongya.core.util;

import java.util.List;
import java.util.Optional;

public class OU<M> {
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
