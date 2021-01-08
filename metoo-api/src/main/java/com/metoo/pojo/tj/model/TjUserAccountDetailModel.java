package com.metoo.pojo.tj.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户消费记录表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class TjUserAccountDetailModel implements Serializable {

    private Integer id;

    private Integer uid;

    private BigDecimal acPoints;

    private BigDecimal balance;

    private BigDecimal psPoints;

    private BigDecimal psCoin;

    private BigDecimal balanceFrozen;

    private BigDecimal acPointsUsed;

    private BigDecimal psPointsUsed;

    private Date createTime;

    private Date updateTime;

}
