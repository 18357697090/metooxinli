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
 * 添加好友申请记录表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ImAddFriendMessage对象", description="添加好友申请记录表")
public class ImAddFriendMessage extends Model<ImAddFriendMessage> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "好友请求发送者id")
    private Integer sendId;

    @ApiModelProperty(value = "备用")
    private String spare;

    @ApiModelProperty(value = "1表示未处理，0表示处理了")
    private Integer state;

    @ApiModelProperty(value = "用户id")
    private Integer uid;

    @ApiModelProperty(value = "好友请求消息内容")
    private String message;

    @ApiModelProperty(value = "请求发起时间")
    private LocalDateTime creationTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
