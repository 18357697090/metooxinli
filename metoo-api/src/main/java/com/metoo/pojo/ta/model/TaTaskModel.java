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

    // 发布人头像
    private String headImg;
    // 人不如昵称
    private String nickName;
    // 是否可以领取 1: 已经领取 0:可以领取
    private Integer getStatus;

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
