package com.metoo.ps.ps.dao.entity;

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
 * 心理测量量表用户评论表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PsComment对象", description="心理测量量表用户评论表")
public class PsComment extends Model<PsComment> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "评论内容")
    private String comment;

    @ApiModelProperty(value = "评论时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "量表id")
    private Integer scaleId;

    @ApiModelProperty(value = "用户uid")
    private Integer uid;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
