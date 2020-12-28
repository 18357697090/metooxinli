package com.metoo.web.controller.im;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 聊天室消息记录表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/im/im-audio-room-chat-record")
public class ImAudioRoomChatRecordController {

    //查看群的聊天记录
    @GetMapping("/audioRoomChatRecord")
    public List<String> audioRoomChatRecord(Integer audioRoomId, Integer page){
        Pageable pageable = PageRequest.of(page,20, Sort.Direction.DESC,"id");
        List<AudioRoomChatRecord> audioRoomChatRecords=  audioRoomChatRecordDao.findByAudioRoomId(audioRoomId,pageable);
        List<String> strings = new ArrayList<>();
        for (AudioRoomChatRecord audioRoomChatRecord : audioRoomChatRecords){
            String content = audioRoomChatRecord.getContent();
            strings.add(content);
        }
        return strings;
    }

}
