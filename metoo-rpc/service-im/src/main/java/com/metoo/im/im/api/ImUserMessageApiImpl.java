package com.metoo.im.im.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.im.ImUserMessageApi;
import com.metoo.im.im.dao.entity.ImUserMessage;
import com.metoo.im.im.service.ImUserMessageService;
import com.metoo.pojo.im.model.ImUserMessageModel;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public RE uid(Integer uid) {
        List<ImUserMessage> imUserMessages = imUserMessageService.uid(uid);
        if (OU.isBlack(imUserMessages)){
            return RE.noData();
        }
        List<ImUserMessageModel> imUserMessageModels = imUserMessages.stream().flatMap(e->{
            return Stream.of(mapper.map(e, ImUserMessageModel.class));
        }).collect(Collectors.toList());
        return RE.ok(imUserMessageModels);
    }

    @Override
    public void save(ImUserMessageModel userMessage) {
        imUserMessageService.save(mapper.map(userMessage,ImUserMessage.class));
    }

    @Override
    public void updateState(Integer uid) {
        imUserMessageService.updateState(uid);
    }

    @Override
    public void deleteByUid(Integer uid) {
        imUserMessageService.deleteByUid(uid);
    }
}
