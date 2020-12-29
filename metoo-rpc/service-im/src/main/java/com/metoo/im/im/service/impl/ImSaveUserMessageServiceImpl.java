package com.metoo.im.im.service.impl;

import com.metoo.im.im.dao.entity.ImSaveUserMessage;
import com.metoo.im.im.dao.mapper.ImSaveUserMessageMapper;
import com.metoo.im.im.dao.repository.ImSaveUserMessageRepository;
import com.metoo.im.im.service.ImSaveUserMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;

/**
 * <p>
 * 用户离线聊天记录表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class ImSaveUserMessageServiceImpl extends ServiceImpl<ImSaveUserMessageMapper, ImSaveUserMessage> implements ImSaveUserMessageService {

    @Autowired
    private ImSaveUserMessageRepository imSaveUserMessageRepository;

    @Override
    public List<ImSaveUserMessage> findByUidAndSendId(Integer uid, Integer sendId) {
        return imSaveUserMessageRepository.findByUidAndSendId(uid,sendId);
    }
}
