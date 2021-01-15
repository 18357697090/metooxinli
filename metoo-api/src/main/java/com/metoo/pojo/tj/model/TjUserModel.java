package com.metoo.pojo.tj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class TjUserModel implements Serializable {
    private Integer id;

    private Integer state;

    private String username;

    private String phone;

    private String extendId;

    private String openId;

    private String assectToken;

    private Date createTime;

    private Date updateTime;

    private TjUserInfoModel tjUserInfoModel;
}
