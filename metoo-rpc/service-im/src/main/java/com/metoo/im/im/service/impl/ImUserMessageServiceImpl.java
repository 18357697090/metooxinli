package com.metoo.im.im.service.impl;

import com.metoo.im.im.dao.entity.ImUserMessage;
import com.metoo.im.im.dao.mapper.ImUserMessageMapper;
import com.metoo.im.im.dao.repository.ImUserMessageRepository;
import com.metoo.im.im.service.ImUserMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户聊天记录表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class ImUserMessageServiceImpl extends ServiceImpl<ImUserMessageMapper, ImUserMessage> implements ImUserMessageService {

    @Autowired
    private ImUserMessageRepository imUserMessageRepository;

    @Override
    public int updateState(int uid) {
        return imUserMessageRepository.updateState(uid);
    }

    @Override
    public int deleteByUid(int uid) {
        return imUserMessageRepository.deleteByUid(uid);
    }

    @Override
    public int aaa(Integer name) {
        return imUserMessageRepository.aaa(name);
    }

    @Override
    public List<Object> bbb(Integer name) {
        return imUserMessageRepository.bbb(name);
    }

    @Override
    public List<ImUserMessage> uid(Integer uid) {
        return imUserMessageRepository.uid(uid);
    }
}
