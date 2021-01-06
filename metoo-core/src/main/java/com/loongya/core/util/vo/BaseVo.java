package com.loongya.core.util.vo;

import com.loongya.core.util.OU;
import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseVo implements Serializable {
    private int pagenum = 1;
    private int pagesize = 10;

    public void setPagenum(Integer pagenum){
        if(OU.isBlack(pagenum)){
            this.pagenum = 1;
        }else {
            this.pagenum = pagenum;
        }
    }

    public void setPagesize(Integer pagesize){
        if(OU.isBlack(pagesize)){
            this.pagesize = 10;
        }else {
            this.pagesize = pagesize;
        }
    }

}
