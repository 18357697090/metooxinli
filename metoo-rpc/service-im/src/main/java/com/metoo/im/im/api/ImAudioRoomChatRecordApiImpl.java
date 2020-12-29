package com.metoo.im.im.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.im.ImAudioRoomChatRecordApi;
import com.metoo.im.im.dao.entity.ImAudioRoomChatRecord;
import com.metoo.im.im.service.ImAudioRoomChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 聊天室消息记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public class ImAudioRoomChatRecordApiImpl implements ImAudioRoomChatRecordApi {

    @Autowired
    private ImAudioRoomChatRecordService imAudioRoomChatRecordService;

    @Override
    public RE audioRoomChatRecord(Integer audioRoomId, Integer page) {
        Pageable pageable = PageRequest.of(page,20, Sort.Direction.DESC,"id");
        List<ImAudioRoomChatRecord> audioRoomChatRecords=  imAudioRoomChatRecordService.findByAudioRoomId(audioRoomId,pageable);
        List<String> strings = new ArrayList<>();
        for (ImAudioRoomChatRecord audioRoomChatRecord : audioRoomChatRecords){
            String content = audioRoomChatRecord.getContent();
            strings.add(content);
        }
        if(OU.isBlack(strings)){
            return RE.noData();
        }
        return RE.ok(strings);
    }
}
