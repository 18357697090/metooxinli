package com.metoo.user.tj.dao.entity;

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
 * 用户举报表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TjReport对象", description="用户举报表")
public class TjReport extends Model<TjReport> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime creationTime;

    @ApiModelProperty(value = "举报内容")
    private String content;

    @ApiModelProperty(value = "举报图片")
    private String picture;

    @ApiModelProperty(value = "用户uid")
    private Integer uid;

    @ApiModelProperty(value = "是否被处理了")
    private Integer state;

    @ApiModelProperty(value = "举报类型")
    private String type;

    @ApiModelProperty(value = "举报id")
    private Integer reportId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
