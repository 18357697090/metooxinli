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
 * 心理测量量表表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PsScale对象", description="心理测量量表表")
public class PsScale extends Model<PsScale> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "量表内容介绍")
    private String content;

    @ApiModelProperty(value = "量表段位")
    private Integer dw;

    @ApiModelProperty(value = "量表名称")
    private String name;

    @ApiModelProperty(value = "量表多少个人测过")
    private Integer number;

    @ApiModelProperty(value = "多少道题目")
    private Integer numberOfProblem;

    @ApiModelProperty(value = "量表图片")
    private String picture;

    @ApiModelProperty(value = "价格")
    private BigDecimal prices;

    @ApiModelProperty(value = "属于的量表集合id")
    private Integer scaleGatherId;

    @ApiModelProperty(value = "量表id")
    private Integer scaleId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "备用")
    private Integer spare;

    @ApiModelProperty(value = "0正常，1不展示")
    private Integer state;

    @ApiModelProperty(value = "量表类型")
    private Integer type;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
