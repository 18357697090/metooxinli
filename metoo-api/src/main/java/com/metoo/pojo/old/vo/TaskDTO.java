package com.metoo.pojo.old.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TaskDTO {
    private String name;
    private String picture;
    private Integer taskId;
    //任务发起人
    private Integer uid;
    //任务标题
    private String taskTitle;
    //任务内容
    private String taskContent;
    //任务指定
    private Integer taskAppoint;
    //任务金额
    private BigDecimal taskPrices;
    //任务段位
    private Integer taskLevel;
    //发布时间
    private String creationTime;

}
