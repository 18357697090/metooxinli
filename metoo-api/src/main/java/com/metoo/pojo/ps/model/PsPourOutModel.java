package com.metoo.pojo.ps.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 心理倾诉师表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PsPourOutModel implements Serializable {
    private Long id;

    private String label;

    private String level;

    private String name;

    private Integer uid;

    private String content;

    private Integer prices;

    private String picture;

    private Integer sort;

    private String spare;

    private Integer onLine;
}
