package com.metoo.pojo.ta.model;

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
public class TaTaskUserModel implements Serializable {

    private String headImg;
    private String nickName;

    private Integer id;

    private Integer taskId;

    private Integer uid;

    private Integer status;

    private BigDecimal price;

    private Date createTime;

    private Date updateTime;
}
