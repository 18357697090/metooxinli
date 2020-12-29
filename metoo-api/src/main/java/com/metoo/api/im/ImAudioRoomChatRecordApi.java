package com.metoo.api.im;

import com.loongya.core.util.RE;


/**
 * <p>
 * 聊天室消息记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface ImAudioRoomChatRecordApi {

    RE audioRoomChatRecord(Integer audioRoomId, Integer page);
}
