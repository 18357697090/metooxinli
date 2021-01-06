package com.metoo.pojo.tj.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户个人信息表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class TjUserInfoModel implements Serializable {

    private Integer id;

    private Integer age;

    private Integer level;

    private Integer gender;

    private String nickName;

    private Integer uid;

    private String headImg;

    private String motto;

    private String prov;

    private String provCode;

    private String city;

    private String cityCode;

    private String area;

    private String areaCode;

}
