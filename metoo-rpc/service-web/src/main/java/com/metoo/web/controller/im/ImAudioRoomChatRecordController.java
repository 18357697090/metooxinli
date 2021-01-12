package com.metoo.web.controller.im;


import com.loongya.core.util.RE;
import com.metoo.api.im.ImAudioRoomChatRecordApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
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

    @DubboReference
    private ImAudioRoomChatRecordApi imAudioRoomChatRecordApi;

    //查看群的聊天记录
    @PostMapping("/audioRoomChatRecord")
    public RE audioRoomChatRecord(Integer audioRoomId, Integer page){
        return imAudioRoomChatRecordApi.audioRoomChatRecord(audioRoomId,page);

    }

}
