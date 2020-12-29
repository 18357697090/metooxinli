package com.metoo.pojo.ps.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class PsPsychologyConsultModel implements Serializable {
    private Long id;

    private String content;

    private String label;

    private String level;

    private String name;

    private Integer onLine;

    private String picture;

    private Integer prices;

    private Integer sort;

    private String spare;

    private Integer uid;
}
