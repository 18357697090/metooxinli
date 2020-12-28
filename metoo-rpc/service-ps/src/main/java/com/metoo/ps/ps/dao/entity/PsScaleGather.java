package com.metoo.ps.ps.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 心理测量量表集合表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PsScaleGather对象", description="心理测量量表集合表")
public class PsScaleGather extends Model<PsScaleGather> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "量表集合段位")
    private Integer dw;

    @ApiModelProperty(value = "集合名称")
    private String name;

    @ApiModelProperty(value = "集合图片")
    private String picture;

    @ApiModelProperty(value = "集合id")
    private Integer scaleGatherId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
