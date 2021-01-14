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
 * 我的加入的聊天室表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Entity
@Table(name = "xy_my_room")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="XyMyRoom对象", description="我的加入的聊天室表")
public class XyMyRoom extends Model<XyMyRoom> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "国家id")
    private Integer countryId;

    @ApiModelProperty(value = "国主id")
    private Integer countryUid;

    @ApiModelProperty(value = "城市id")
    private Integer cityId;

    @ApiModelProperty(value = "城主id")
    private Integer cityUid;

    @ApiModelProperty(value = "成员id")
    private Integer uid;

    @ApiModelProperty(value = "用户拓展id")
    private String extendId;

    @ApiModelProperty(value = "1: 表示正常 2: 退出 3: 强制退出")
    private Integer status;

    @ApiModelProperty(value = "房间公告")
    private String notice;

    @ApiModelProperty(value = "1:普通城市 2: 主城 3: 小部落")
    private Integer type;

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
