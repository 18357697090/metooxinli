package com.metoo.pojo.nr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class NrGoodsModel implements Serializable {
    private Integer id;

    private String content;

    private String name;

    private String img;

    private Integer price;

    private String remark;

    private Date createTime;

    private Date updateTime;

}
