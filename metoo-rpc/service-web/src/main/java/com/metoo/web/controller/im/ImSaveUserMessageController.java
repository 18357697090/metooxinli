package com.metoo.web.controller.im;


import com.loongya.core.util.RE;
import com.metoo.api.im.ImSaveUserMessageApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
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

    @DubboReference
    private ImSaveUserMessageApi imSaveUserMessageApi;

    //查看聊天记录
        @PostMapping("/chatRecord")
        public RE chatRecord(@RequestHeader("UID")Integer uid, Integer sendId){
            return imSaveUserMessageApi.findByUidAndSendId(uid,sendId);
        }

}
