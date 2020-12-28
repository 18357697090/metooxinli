package com.metoo.pojo.vo.ta;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RwDTO {
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
