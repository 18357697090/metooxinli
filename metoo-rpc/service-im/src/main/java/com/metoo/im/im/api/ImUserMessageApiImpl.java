package com.metoo.im.im.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.im.ImUserMessageApi;
import com.metoo.im.im.dao.entity.ImUserMessage;
import com.metoo.im.im.service.ImUserMessageService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 用户聊天记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
public class ImUserMessageApiImpl implements ImUserMessageApi {

    @Autowired
    private ImUserMessageService imUserMessageService;

    @Override
    public RE uid(Integer uid) {
        List<ImUserMessage> imUserMessages = imUserMessageService.uid(uid);
        if (OU.isBlack(imUserMessages)){
            return RE.noData();
        }
        return RE.ok(imUserMessageService);
    }
}
