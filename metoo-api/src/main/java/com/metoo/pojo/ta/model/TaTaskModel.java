package com.metoo.pojo.ta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 任务大厅表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class TaTaskModel implements Serializable {

    private Integer id;

    private Integer uid;

    private String title;

    private String content;

    private BigDecimal price;

    private Integer state;

    private Integer type;

    private String remark;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private Date updateTime;

}
