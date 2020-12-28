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
 * 心理倾诉师表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PsPourOut对象", description="心理倾诉师表")
public class PsPourOut extends Model<PsPourOut> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "倾诉师标签")
    private String label;

    @ApiModelProperty(value = "倾诉师等级")
    private String level;

    @ApiModelProperty(value = "倾诉师名字")
    private String name;

    @ApiModelProperty(value = "倾诉师uid")
    private Integer uid;

    @ApiModelProperty(value = "倾诉师内容介绍")
    private String content;

    @ApiModelProperty(value = "倾诉师的价格")
    private Integer prices;

    @ApiModelProperty(value = "倾诉师的头像")
    private String picture;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "备用字段")
    private String spare;

    @ApiModelProperty(value = "是否在线。1在线0不在线")
    private Integer onLine;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
