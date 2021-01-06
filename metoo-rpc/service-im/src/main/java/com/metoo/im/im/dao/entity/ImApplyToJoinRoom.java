package com.metoo.im.im.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * 申请加入聊天室记录表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Entity
@Table(name = "im_apply_to_join_room")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ImApplyToJoinRoom对象", description="申请加入聊天室记录表")
public class ImApplyToJoinRoom extends Model<ImApplyToJoinRoom> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @CreatedDate
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

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
