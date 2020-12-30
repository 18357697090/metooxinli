package com.loongya.core.util;




import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RE<M> implements Serializable {
    //返回状态 [0-成功,1-currentService.999-系统异常.-4-没有数据,列表为空.-1-参数有误]
    private int code;
    private String msg;
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
        RE.setCode(status);
        RE.setData(data);
        RE.setMsg(msg);
        return RE;
    }
    public static<M> RE noData(M data){
        RE RE = new RE();
        RE.setCode(0);
        RE.setData(data);
        RE.setMsg("暂无数据!");
        return RE;
    }
    public static<M> RE noData(){
        RE RE = new RE();
        RE.setCode(0);
        RE.setMsg("暂无数据!");
        return RE;
    }

    public static<M> RE error(String msg){
        RE RE = new RE();
        RE.setCode(999);
        RE.setMsg(msg);
        return RE;
    }

    public static<M> RE fail(String msg){
        return fail(1,msg);
    }
    public static<M> RE fail(){
        return fail(1,"error");
    }
    public static<M> RE fail(int status, String msg){
        RE RE = new RE();
        RE.setCode(status);
        RE.setMsg(msg);
        return RE;
    }
    public static<M> RE paramError(String msg){
        RE RE = new RE();
        RE.setCode(-1);
        RE.setMsg(msg);
        return RE;
    }
}
