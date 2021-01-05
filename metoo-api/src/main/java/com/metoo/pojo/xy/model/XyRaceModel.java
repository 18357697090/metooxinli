package com.metoo.pojo.xy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 国度，族表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class XyRaceModel implements Serializable {
    private Long id;

    private Date createTime;

    private String introduction;

    private String name;

    private Integer raceId;

    private String picture;

}
