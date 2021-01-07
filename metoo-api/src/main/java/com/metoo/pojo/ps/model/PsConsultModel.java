package com.metoo.pojo.ps.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 心理咨询师表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PsConsultModel implements Serializable {

    private Integer status; // 1: 正常  2：服务中 3：有待继续的咨询

    private Integer id;

    private String content;

    private String label;

    private String level;

    private String name;

    private Integer onLine;

    private String headImg;

    private BigDecimal price;

    private Integer sort;

    private String spare;

    private Integer uid;
}
