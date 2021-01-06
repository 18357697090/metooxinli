package com.metoo.pojo.in.model;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class InAreaModel implements Serializable {

    private JSONArray list;

    private Integer id;

    private String code;

    private String name;

    private Integer pid;

    private String pcode;

    private String pname;

    private Integer level;

    private String pcodes;

    private String pcodenames;
}
