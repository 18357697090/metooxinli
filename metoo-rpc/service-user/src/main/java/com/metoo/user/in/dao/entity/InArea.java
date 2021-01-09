package com.metoo.user.in.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * <p>
 * 区域表
 * </p>
 *
 * @author loongya
 * @since 2021-01-05
 */
@Entity
@Table(name = "in_area")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="InArea对象", description="区域表")
public class InArea extends Model<InArea> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "区域编码")
    private String code;

    @ApiModelProperty(value = "区域名称")
    private String name;

    @ApiModelProperty(value = "父id")
    private Integer pid;

    @ApiModelProperty(value = "父级区域编码")
    private String pcode;

    @ApiModelProperty(value = "父级区域名称")
    private String pname;

    @ApiModelProperty(value = "级别")
    private Integer level;

    @ApiModelProperty(value = "所有上级编码")
    private String pcodes;

    @ApiModelProperty(value = "所有上级名称")
    private String pcodenames;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
