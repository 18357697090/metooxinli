package com.metoo.im.im.service;

import com.metoo.im.im.dao.entity.ImAudioRoomChatRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p>
 * 聊天室消息记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface ImAudioRoomChatRecordService extends IService<ImAudioRoomChatRecord> {
    List<ImAudioRoomChatRecord> findByAudioRoomId(Integer audioRoomId, Pageable pageable);
}
