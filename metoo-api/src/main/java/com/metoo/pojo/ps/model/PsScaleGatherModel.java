package com.metoo.pojo.ps.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 心理测量量表集合表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PsScaleGatherModel implements Serializable {
    private Integer id;

    private Integer dw;

    private BigDecimal price;

    private String name;

    private String picture;

    private Integer scaleGatherId;
}
