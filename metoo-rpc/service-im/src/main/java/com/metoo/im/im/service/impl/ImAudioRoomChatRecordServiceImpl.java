package com.metoo.im.im.service.impl;

import com.metoo.im.im.dao.entity.ImAudioRoomChatRecord;
import com.metoo.im.im.dao.mapper.ImAudioRoomChatRecordMapper;
import com.metoo.im.im.dao.repository.ImAudioRoomChatRecordRepository;
import com.metoo.im.im.service.ImAudioRoomChatRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 聊天室消息记录表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class ImAudioRoomChatRecordServiceImpl extends ServiceImpl<ImAudioRoomChatRecordMapper, ImAudioRoomChatRecord> implements ImAudioRoomChatRecordService {

    @Autowired
    private ImAudioRoomChatRecordRepository imAudioRoomChatRecordRepository;

    @Override
    public List<ImAudioRoomChatRecord> findByAudioRoomId(Integer audioRoomId, Pageable pageable) {
        return imAudioRoomChatRecordRepository.findByAudioRoomId(audioRoomId,pageable);
    }
}
