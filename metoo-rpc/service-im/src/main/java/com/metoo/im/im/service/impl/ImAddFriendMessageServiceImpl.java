package com.metoo.im.im.service.impl;

import com.loongya.core.util.RE;
import com.metoo.im.im.dao.entity.ImAddFriendMessage;
import com.metoo.im.im.dao.entity.ImFriend;
import com.metoo.im.im.dao.mapper.ImAddFriendMessageMapper;
import com.metoo.im.im.dao.repository.ImAddFriendMessageRepository;
import com.metoo.im.im.dao.repository.ImFriendRepository;
import com.metoo.im.im.service.ImAddFriendMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metoo.pojo.im.vo.ImAddFriendMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 添加好友申请记录表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class ImAddFriendMessageServiceImpl extends ServiceImpl<ImAddFriendMessageMapper, ImAddFriendMessage> implements ImAddFriendMessageService {

    @Autowired
    private ImAddFriendMessageRepository imAddFriendMessageRepository;


    @Override
    public ImAddFriendMessage findByUidAndSendId(Integer uid, Integer sendId) {
        return imAddFriendMessageRepository.findByUidAndSendId(uid,sendId);
    }

    @Override
    public List<ImAddFriendMessage> ByUidAndState(Integer uid) {
        return imAddFriendMessageRepository.ByUidAndState(uid);
    }

    @Override
    public ImAddFriendMessage UidSendIdState(Integer uid, Integer friendId) {
        return imAddFriendMessageRepository.UidSendIdState(uid,friendId);
    }

    @Override
    public int againRequest(String message, int uid, int sendId) {
        return imAddFriendMessageRepository.againRequest(message,uid,sendId);
    }

    @Override
    public int updateState(int uid, int sendId) {
        return imAddFriendMessageRepository.updateState(uid,sendId);
    }
}
