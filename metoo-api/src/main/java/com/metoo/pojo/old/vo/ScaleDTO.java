package com.metoo.pojo.old.vo;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
public class ScaleDTO {
    private int scaleId;
    @Column(nullable = false,columnDefinition="mediumtext")
    //表示该量表有多少人测量了
    private int number;
    @Column(nullable = false,columnDefinition="mediumtext")
    private String picture;
    private BigDecimal prices=new BigDecimal(0);
    private String name;
    private String content;
    private Integer state;
    //0表示其他测量 大于0的表示该段位的测量
    private Integer dw;
    //scaleGatherId表示该量表属于哪套题。
    private Integer scaleGatherId;
    //表示该题目的类型，需要计算哪些分数
    private Integer type;
    private Integer numberOfProblem;
    //1表示是在精品测量里显示
    private Integer spare;
    //后续用来调整排序
    private Integer sort;
}
