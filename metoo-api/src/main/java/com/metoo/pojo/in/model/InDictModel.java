package com.metoo.pojo.in.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 首页轮播图
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class InDictModel implements Serializable {

    private Integer id;

    private String name;

    private String pkey;

    private String key;

    private String value;

    private String remark;

}
