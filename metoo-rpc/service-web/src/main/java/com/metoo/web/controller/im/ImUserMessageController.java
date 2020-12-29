package com.metoo.web.controller.im;


import com.loongya.core.util.RE;
import com.metoo.api.im.ImUserMessageApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户聊天记录表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/im/im-user-message")
public class ImUserMessageController {
    @DubboReference
    private ImUserMessageApi imUserMessageApi;

    //取离线消息
    @GetMapping("/offlineMessage")
    public RE offlineMessage(@RequestHeader("UID")Integer uid){
        return imUserMessageApi.uid(uid);
    }


}
