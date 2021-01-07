package com.metoo.xy.xy.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * <p>
 * 我的加入的聊天室表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Entity
@Table(name = "xy_my_room")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="XyMyRoom对象", description="我的加入的聊天室表")
public class XyMyRoom extends Model<XyMyRoom> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @CreatedDate
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "是否为主任")
    private Integer isHost;

    @ApiModelProperty(value = "0表示退出或者被T  1表示正常")
    private Integer state;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "uid")
    private Integer uid;

    @ApiModelProperty(value = "房间id")
    private Integer myRoomId;

    @ApiModelProperty(value = "房间公告")
    private String notice;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
