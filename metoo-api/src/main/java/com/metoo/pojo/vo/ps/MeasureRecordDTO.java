package com.metoo.pojo.vo.ps;

import lombok.Data;

import java.util.Date;

@Data
public class MeasureRecordDTO {
    private Integer scaleId;
    private Integer score;
    private String content;
    private String scaleName;
    private Date createtime;
}
