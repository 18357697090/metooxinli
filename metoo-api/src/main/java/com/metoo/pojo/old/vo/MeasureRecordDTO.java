package com.metoo.pojo.old.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MeasureRecordDTO  implements Serializable {
    private Integer scaleId;
    private Integer score;
    private String content;
    private String scaleName;
    private Date createtime;
}
