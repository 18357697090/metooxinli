package com.metoo.order.im.dao.entity;

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
 * 礼物记录表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ImGiftRecord对象", description="礼物记录表")
public class ImGiftRecord extends Model<ImGiftRecord> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "送礼物的人id")
    private Integer uid;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime creationTime;

    @ApiModelProperty(value = "接受礼物的用户id")
    private Integer accepted;

    @ApiModelProperty(value = "礼物id")
    private Integer giftId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
