package com.metoo.pojo.ps.model;

import com.metoo.pojo.tj.model.TjUserInfoModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
public class PsPourOutOrderModel implements Serializable {

    private PsPourOutModel psPourOutModel;

    private TjUserInfoModel tjUserInfoModel;

    private Integer id;

    private Integer pourId;

    private Integer userId;

    private Integer status;

    private BigDecimal price;

    private Date createTime;

    private Date updateTime;
}
