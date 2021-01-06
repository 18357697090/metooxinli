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
 * 用户离线聊天记录表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Entity
@Table(name = "im_save_user_message")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ImSaveUserMessage对象", description="用户离线聊天记录表")
public class ImSaveUserMessage extends Model<ImSaveUserMessage> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "消息内容")
    @Column(name = "message", columnDefinition = "text")
    private String message;

    @ApiModelProperty(value = "发送的用户uid")
    private Integer sendId;

    @ApiModelProperty(value = "接受消息的uid")
    private Integer uid;

    @CreatedDate
    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
