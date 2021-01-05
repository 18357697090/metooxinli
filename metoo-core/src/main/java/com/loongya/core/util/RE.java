package com.loongya.core.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RE<T> implements Serializable {
    private String code;
    private String msg;
    private T data;
    private boolean fail = true;

    private RE(CommEnum commEnum, T data){
        this.code = commEnum.getCode();
        if(commEnum.getCode().equals("0")){
            this.fail = false;
        }
        this.msg = commEnum.getMsg();
        this.data = data;
    }
    private RE(String code, String msg, T data){
        this.code = code;
        if(code.equals("0")){
            this.fail = false;
        }
        this.msg = msg;
        this.data = data;
    }
    private RE(CommEnum commEnum){
        this.code = commEnum.getCode();
        if(commEnum.getCode().equals("0")){
            this.fail = false;
        }
        this.msg = commEnum.getMsg();
        this.data = null;
    }

    public static<T> RE ok(){
        return ok(CommsEnum.SUCCESS,"ok");
    }
    public static<T> RE ok(T data){
        return ok(CommsEnum.SUCCESS,data);
    }

    public static<T> RE ok(CommEnum commEnum,T data){
        return new RE(commEnum, data);
    }

    public static<T> RE noData(T data){
        return new RE(CommsEnum.NO_DATA,data);
    }
    public static<T> RE noData(){
        return new RE(CommsEnum.NO_DATA,null);
    }

    public static<T> RE fail(CommEnum commEnum, T data){
        return new RE(commEnum, data);
    }
    public static<T> RE fail(CommEnum commEnum){
        return new RE(commEnum);
    }
    public static<T> RE fail(String msg){
        return new RE("1", msg, null);
    }
    public static<T> RE fail(String code, String msg){
        return new RE(code, msg, null);
    }
}
