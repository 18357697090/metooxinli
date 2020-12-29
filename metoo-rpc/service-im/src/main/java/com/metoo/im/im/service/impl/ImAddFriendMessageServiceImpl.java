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
    @Autowired
    private ImFriendRepository imFriendRepository;

    @Override
    public RE AddFriend(Integer uid,Integer friendId,String message) {
        ImFriend friend = imFriendRepository.findByUidAndFriendId(uid,friendId);
        ImAddFriendMessage ImAddFriendMessage= imAddFriendMessageRepository.findByUidAndSendId(friendId,uid);
        if (friend!=null){
            if(friend.getState().equals("1")){
                return RE.serviceFail("exist");
            }else if (friend.getState().equals("2")){
                if(ImAddFriendMessage!=null){
                    if (ImAddFriendMessage.getState().equals(1)){
                        return RE.serviceFail("error");
                    }else {
                        imAddFriendMessageRepository.againRequest(message,friendId,uid);
                        return RE.serviceFail("success");
                    }
                }else {
                    ImAddFriendMessage addFriendMessage1 = new ImAddFriendMessage();
                    addFriendMessage1.setUid(friendId);
                    addFriendMessage1.setSendId(uid);
                    addFriendMessage1.setMessage(message);
                    addFriendMessage1.setState(1);
                    imAddFriendMessageRepository.save(addFriendMessage1);
                    return RE.serviceFail("success");
                }
            }else if(friend.getState().equals("3")){
                return RE.serviceFail("error");
            }
        }else {
            if(ImAddFriendMessage!=null){
                if (ImAddFriendMessage.getState().equals(1)){
                    return RE.serviceFail("error");
                }else {
                    imAddFriendMessageRepository.againRequest(message,uid,friendId);
                    return RE.serviceFail("success");
                }
            }else {
                ImAddFriendMessage addFriendMessage1 = new ImAddFriendMessage();
                addFriendMessage1.setUid(friendId);
                addFriendMessage1.setSendId(uid);
                addFriendMessage1.setMessage(message);
                addFriendMessage1.setState(1);
                imAddFriendMessageRepository.save(addFriendMessage1);
                return RE.serviceFail("success");
            }
        }
        return RE.serviceFail("success");
    }

    @Override
    public RE FriendRequest(Integer uid) {
        List<ImAddFriendMessage> addFriendMessages = imAddFriendMessageRepository.ByUidAndState(uid);
        List<ImAddFriendMessageVo> friendListDtos = new ArrayList<>();
        for(ImAddFriendMessage ImAddFriendMessage : addFriendMessages){
            int uid1 = ImAddFriendMessage.getSendId();

            TjUserInfo TjUserInfo = userInfoDao.findByUid(uid1);
            ImFriendListDto ImFriendListDto = mapper.map(TjUserInfo,ImFriendListDto.class);
            ImFriendListDto.setMotto(ImAddFriendMessage.getMessage());
            friendListDtos.add(ImFriendListDto);
        }
        return null;
    }

    @Override
    public RE HandlerFriendRequest(Integer uid, Integer sendId, Integer handle) {

        imAddFriendMessageRepository.updateState(uid,sendId);
        Friend friend1 = friendDao.findByUidAndFriendId(sendId,uid);
        if (friend1==null){
            if (Handle==1){
                Friend friend = new Friend();
                friend.setUid(uid);
                friend.setFriendId(sendId);
                friendDao.save(friend);
                Friend friend11 = new Friend();
                friend11.setUid(sendId);
                friend11.setFriendId(uid);
                friendDao.save(friend11);
            }
        }else {
            if(friend1.getState()!=1){
                if (Handle==1){
                    friendDao.updateFriendState(sendId,uid);
                }
            }
        }

        return null;
    }
}
