package com.loongya.core.util;




import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RE<M> implements Serializable {
    //返回状态 [0-成功,1-currentService.999-系统异常.-4-没有数据,列表为空.-1-参数有误]
    private int errcode;
    private String errmsg;
    private M data;
    private RE(){}

    public static<M> RE ok(M data){
        return ok(0,data,"操作成功");
    }
    public static<M> RE ok(){
        return ok(0,null,"操作成功");
    }
    public static<M> RE ok(M data, String msg){
        if(OU.isBlack(msg)){
            msg = "操作成功";
        }
        return ok(0,data,msg);
    }
    public static<M> RE ok(int status, M data, String msg){
        RE RE = new RE();
        RE.setErrcode(status);
        RE.setData(data);
        RE.setErrmsg(msg);
        return RE;
    }
    public static<M> RE noData(M data){
        RE RE = new RE();
        RE.setErrcode(0);
        RE.setData(data);
        RE.setErrmsg("暂无数据!");
        return RE;
    }
    public static<M> RE noData(){
        RE RE = new RE();
        RE.setErrcode(0);
        RE.setErrmsg("暂无数据!");
        return RE;
    }

    public static<M> RE systemError(String msg){
        RE RE = new RE();
        RE.setErrcode(999);
        RE.setErrmsg(msg);
        return RE;
    }

    public static<M> RE serviceFail(String msg){
        return serviceFail(1,msg);
    }
    public static<M> RE serviceFail(int status, String msg){
        RE RE = new RE();
        RE.setErrcode(status);
        RE.setErrmsg(msg);
        return RE;
    }
    public static<M> RE paramError(String msg){
        RE RE = new RE();
        RE.setErrcode(-1);
        RE.setErrmsg(msg);
        return RE;
    }
}
