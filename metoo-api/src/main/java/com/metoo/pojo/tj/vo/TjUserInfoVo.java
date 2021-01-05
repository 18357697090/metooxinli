package com.metoo.pojo.tj.vo;

import com.loongya.core.util.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class TjUserInfoVo extends BaseVo {

    private Integer userId;

    private String headImg;

    private String nickName;

    private Integer gender;

    private String motto;

    private String birthday;

    private String prov;

    private String provCode;

    private String city;

    private String cityCode;

    private String area;

    private String areaCode;


}
