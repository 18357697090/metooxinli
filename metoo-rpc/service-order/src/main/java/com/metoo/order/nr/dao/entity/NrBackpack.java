package com.metoo.order.nr.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户背包商品表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="NrBackpack对象", description="用户背包商品表")
public class NrBackpack extends Model<NrBackpack> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户拥有商品数量")
    private Integer number;

    @ApiModelProperty(value = "商品图片")
    private String picture;

    @ApiModelProperty(value = "商品类型id")
    private Integer type;

    @ApiModelProperty(value = "用户uid")
    private Integer uid;

    @ApiModelProperty(value = "商品内容描述")
    private String content;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "备用字段")
    private String spare;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime creationTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
