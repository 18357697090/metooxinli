package com.metoo.web.controller.im;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户离线聊天记录表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/im/im-save-user-message")
public class ImSaveUserMessageController {

    //查看聊天记录
    @GetMapping("/chatRecord")
    public List<saveUserMessage> chatRecord(@RequestHeader("UID")Integer uid, Integer sendId){
        return saveUserMessageDao.findByUidAndSendId(uid,sendId);
    }

}
