package com.metoo.xy.xy.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
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
 * 城表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Entity
@Table(name = "xy_city")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="XyCity对象", description="城表")
public class XyCity extends Model<XyCity> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "国家id")
    private Integer countryId;

    @ApiModelProperty(value = "国主id")
    private Integer countryUid;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "城市创建者")
    private Integer uid;

    @ApiModelProperty(value = "用户拓展id")
    private String extendId;

    @ApiModelProperty(value = "介绍")
    private String detail;

    @ApiModelProperty(value = "头像")
    private String img;

    @ApiModelProperty(value = "1正常 2:异常  3: 删除")
    private Integer status;

    @ApiModelProperty(value = "城市标签: 1 为正常的城，2，商家馆  3，倾诉馆 4，请安馆 5，匹配 , 100:小部落")
    private Integer type;

    @ApiModelProperty(value = "是否主城 0:是 1:否")
    private Integer isMain;

    @CreatedDate
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
