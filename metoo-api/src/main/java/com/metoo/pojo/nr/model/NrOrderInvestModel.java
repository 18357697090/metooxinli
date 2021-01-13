package com.metoo.pojo.nr.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
public class NrOrderInvestModel implements Serializable {
    private Integer id;

    private Integer uid;

    private String orderNo;

    private BigDecimal price;

    private Integer status;

    private String tradeNo;

    private Date payTime;

    private Date createTime;

    private Date updateTime;

}
