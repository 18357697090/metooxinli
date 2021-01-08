package com.metoo.pojo.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户背包商品表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class NrBackpackModel implements Serializable {

    private Integer id;

    private Integer num;

    private String img;

    private Integer goodsId;

    private Integer uid;

    private String content;

    private String name;

    private BigDecimal price;

    private String remark;

    private Date createTime;

    private Date updateTime;
}
