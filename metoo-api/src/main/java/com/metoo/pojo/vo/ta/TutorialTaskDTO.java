package com.metoo.pojo.vo.ta;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TutorialTaskDTO {
    private String name;
    private String picture;
    //任务发起人
    private Integer uid;
    //任务标题
    private String taskTitle;
    //任务金额
    private BigDecimal taskPrices;
    //任务段位
    private Integer taskLevel;
    //任务状态 1表示发布中 0表示被接受
    private Integer state;
    //备用
    private String spare;
}
