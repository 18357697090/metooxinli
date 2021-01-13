package com.metoo.xy.xy.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * <p>
 * 申请加入城消息记录表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Entity
@Table(name = "xy_create_city")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="XyCreateCity对象", description="申请加入城消息记录表")
public class XyCreateCity extends Model<XyCreateCity> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "国id")
    private Integer countryId;

    @ApiModelProperty(value = "群主id(国主id)")
    private Integer countryUid;

    @ApiModelProperty(value = "申请建成用户uid")
    private Integer uid;

    @ApiModelProperty(value = "消耗积分")
    private BigDecimal price;

    @ApiModelProperty(value = "申请内容")
    private String msg;

    @ApiModelProperty(value = "1: 待审核 2: 审核成功 3: 审核失败")
    private Integer status;

    @ApiModelProperty(value = "城名称")
    private String cityName;

    @ApiModelProperty(value = "城市简介")
    private String cityDetail;

    @CreatedDate
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @CreatedDate
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
