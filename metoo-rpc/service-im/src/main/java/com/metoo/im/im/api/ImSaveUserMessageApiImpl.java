package com.metoo.im.im.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.im.ImSaveUserMessageApi;
import com.metoo.im.im.dao.entity.ImSaveUserMessage;
import com.metoo.im.im.service.ImSaveUserMessageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 用户离线聊天记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public class ImSaveUserMessageApiImpl implements ImSaveUserMessageApi {

    @Autowired
    private ImSaveUserMessageService imSaveUserMessageService;

    @Override
    public RE findByUidAndSendId(Integer uid, Integer sendId) {
        List<ImSaveUserMessage> imSaveUserMessages = imSaveUserMessageService.findByUidAndSendId(uid, sendId);
        if (OU.isBlack(imSaveUserMessages)){
            return RE.noData();
        }
        return RE.ok(imSaveUserMessages);
    }
}
