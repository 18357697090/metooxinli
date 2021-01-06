package com.metoo.im.im.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.im.ImSaveUserMessageApi;
import com.metoo.im.im.dao.entity.ImSaveUserMessage;
import com.metoo.im.im.service.ImSaveUserMessageService;
import com.metoo.pojo.im.model.ImSaveUserMessageModel;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户离线聊天记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class ImSaveUserMessageApiImpl implements ImSaveUserMessageApi {

    @Autowired
    private ImSaveUserMessageService imSaveUserMessageService;
    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public RE findByUidAndSendId(Integer uid, Integer sendId) {
        List<ImSaveUserMessage> imSaveUserMessages = imSaveUserMessageService.findByUidAndSendId(uid, sendId);
        if (OU.isBlack(imSaveUserMessages)){
            return RE.noData();
        }
        return RE.ok(imSaveUserMessages);
    }

    @Override
    public void save(ImSaveUserMessageModel imSaveUserMessageModel) {
        imSaveUserMessageService.save(mapper.map(imSaveUserMessageModel,ImSaveUserMessage.class));
    }
}
