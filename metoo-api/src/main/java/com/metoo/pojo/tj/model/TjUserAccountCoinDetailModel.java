package com.metoo.pojo.tj.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户心理币消费记录表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class TjUserAccountCoinDetailModel implements Serializable {
    private BigDecimal price;
    private Integer uid;
    private Integer accountId;
    private String content;
    private Integer type;
    private String typeName;
    private String remark;
    private BigDecimal prePrice;
    private BigDecimal afterPrice;

}
