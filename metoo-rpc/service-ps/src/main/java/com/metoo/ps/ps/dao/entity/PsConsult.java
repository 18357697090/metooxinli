package com.metoo.ps.ps.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * <p>
 * 心理咨询师表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PsConsult对象", description="心理咨询师表")
public class PsConsult extends Model<PsConsult> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "咨询师内容介绍")
    private String content;

    @ApiModelProperty(value = "咨询师标签")
    private String label;

    @ApiModelProperty(value = "咨询师等级")
    private String level;

    @ApiModelProperty(value = "咨询师名称")
    private String name;

    @ApiModelProperty(value = "是否在线。1在线0不在线")
    private Integer onLine;

    @ApiModelProperty(value = "咨询师头像")
    private String headImg;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "备用字段")
    private String spare;

    @ApiModelProperty(value = "咨询师id")
    private Integer uid;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
