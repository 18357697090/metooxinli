package com.metoo.pojo.old.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class PublishTaskDTO implements Serializable {
    //任务标题
    private String taskTitle;
    //任务内容
    private String taskContent;
    //任务指定
    private Integer taskAppoint;
    //任务金额
    private BigDecimal prices=new BigDecimal(0);
    //任务段位
    private Integer taskLevel;
    //任务类型
    private Integer type;
    private String picture;

}
