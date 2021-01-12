package com.metoo.pojo.ps.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 心理测量量表表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PsScaleModel implements Serializable {
    private Integer id;

    private String content;

    private Integer dw;

    private String name;

    private Integer number;

    private Integer numberOfProblem;

    private String picture;

    private BigDecimal prices;

    private Integer scaleGatherId;

    private Integer scaleId;

    private Integer sort;

    private Integer spare;

    private Integer state;

    private Integer type;
}
