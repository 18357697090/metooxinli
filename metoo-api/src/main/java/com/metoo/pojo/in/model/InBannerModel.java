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
public class InBannerModel implements Serializable {

    private Integer id;

    private String title;

    private String link;

    private String img;

    private Integer type;

    private Date createTime;
}
