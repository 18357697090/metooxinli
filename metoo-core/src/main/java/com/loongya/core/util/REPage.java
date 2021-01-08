package com.loongya.core.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class REPage<T> extends RE{
    private Long total;
    private Integer pagesize;
    private Integer pagenum;
    private Long totalPage;


    private REPage(Integer pagenum, Integer pagesize, Long total, T data){
        super(CommsEnum.SUCCESS,data);
        this.pagenum = pagenum;
        this.pagesize = pagesize;
        this.total = total;
        if(OU.isNotBlack(total)){
            this.totalPage = total%pagesize==0?total/pagesize:total/pagesize+1;
        }
    }

    public static<T> REPage ok(Integer pagenum, Integer pagesize, Long total, T data){
        return new REPage(pagenum, pagesize, total, data);
    }
}
