package com.metoo.im.im.dao.entity;

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
 * 申请加入聊天室记录表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ImApplyToJoinRoom对象", description="申请加入聊天室记录表")
public class ImApplyToJoinRoom extends Model<ImApplyToJoinRoom> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime creationTime;

    @ApiModelProperty(value = "房间号")
    private Integer roomId;

    @ApiModelProperty(value = "1表示申请，2表示通过，3表示拒绝")
    private Integer state;

    @ApiModelProperty(value = "用户id")
    private Integer uid;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
