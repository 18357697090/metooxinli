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
 * 聊天室消息记录表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Entity
@Table(name = "im_audio_room_chat_record")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ImAudioRoomChatRecord对象", description="聊天室消息记录表")
public class ImAudioRoomChatRecord extends Model<ImAudioRoomChatRecord> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "聊天室id")
    private Integer audioRoomId;

    @ApiModelProperty(value = "消息内容")
    @Column(name = "content", columnDefinition = "text")
    private String content;

    @CreatedDate
    @ApiModelProperty(value = "发送时间")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
