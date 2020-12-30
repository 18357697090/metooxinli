package com.metoo.pojo.old.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class RwDTO implements Serializable {
    //标题
    private String title;
    //内容
    private String content;
    //是否指定，
    private Integer appoint;
    //价格
    private BigDecimal prices=new BigDecimal(0);
    //指定段位
    private Integer level;

}
