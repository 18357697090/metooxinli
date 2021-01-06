package com.metoo.pojo.im.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 聊天室消息记录表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ImAudioRoomChatRecordModel implements Serializable {
    private long id;


    private Date createTime;

    private Integer AudioRoomId;

    private String content;


}
