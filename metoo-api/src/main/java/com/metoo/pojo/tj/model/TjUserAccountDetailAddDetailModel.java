package com.metoo.pojo.tj.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class TjUserAccountDetailAddDetailModel implements Serializable {
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
