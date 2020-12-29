package com.metoo.im.im.service.impl;

import com.metoo.im.im.dao.entity.ImFriend;
import com.metoo.im.im.dao.mapper.ImFriendMapper;
import com.metoo.im.im.dao.repository.ImFriendRepository;
import com.metoo.im.im.service.ImFriendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 好友列表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class ImFriendServiceImpl extends ServiceImpl<ImFriendMapper, ImFriend> implements ImFriendService {
    @Autowired
    private ImFriendRepository imFriendRepository;


    @Override
    public List<ImFriend> findByUid(Integer uid) {
        return imFriendRepository.findByUid(uid);
    }

    @Override
    public ImFriend findByUidAndFriendIdAndState(Integer uid, Integer friendId) {
        return imFriendRepository.findByUidAndFriendIdAndState(uid,friendId);
    }

    @Override
    public ImFriend findByUidAndFriendId(Integer uid, Integer frindId) {
        return imFriendRepository.findByUidAndFriendId(uid,frindId);
    }

    @Override
    public int updateFriendState(Integer uid, Integer friendId) {
        return imFriendRepository.updateFriendState(uid,friendId);
    }

    @Override
    public int deleteFriendState(Integer uid, Integer friendId) {
        return imFriendRepository.deleteFriendState(uid,friendId);
    }

    @Override
    public int blackFriends(Integer state, Integer uid, Integer friendId) {
        return imFriendRepository.blackFriends(state,uid,friendId);
    }
}
