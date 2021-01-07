package com.metoo.pojo.ps.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 心理倾诉胶囊（备忘录功能）表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PsCapsuleModel implements Serializable {

    private Integer id;

    private Integer authType;

    private Integer readNum;

    private String title;

    private String content;

    private BigDecimal price;

    private Integer type;

    private Integer uid;

    private Integer state;


    private Date createTime;

    private Date updateTime;
}
