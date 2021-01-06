package com.metoo.im.im.api;


import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.im.ImAddFriendMessageApi;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.im.im.dao.entity.ImAddFriendMessage;
import com.metoo.im.im.dao.entity.ImFriend;
import com.metoo.im.im.service.ImAddFriendMessageService;
import com.metoo.im.im.service.ImFriendService;
import com.metoo.pojo.old.vo.FriendListDto;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 添加好友申请记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class ImAddFriendMessageApiImpl implements ImAddFriendMessageApi {

    @DubboReference
    private TjUserInfoApi tjUserInfoApi;

    @Autowired
    private ImAddFriendMessageService imAddFriendMessageService;
    @Autowired
    private ImFriendService imFriendService;
    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public RE AddFriend(Integer uid, Integer friendId, String message) {
        tjUserInfoApi.findByUid(uid);
        ImFriend friend = imFriendService.findByUidAndFriendId(uid,friendId);
        ImAddFriendMessage ImAddFriendMessage= imAddFriendMessageService.findByUidAndSendId(friendId,uid);
        if (friend!=null){
            if(friend.getState().equals("1")){
                return RE.fail("exist");
            }else if (friend.getState().equals("2")){
                if(ImAddFriendMessage!=null){
                    if (ImAddFriendMessage.getState().equals(1)){
                        return RE.fail("error");
                    }else {
                        imAddFriendMessageService.againRequest(message,friendId,uid);
                        return RE.fail("success");
                    }
                }else {
                    ImAddFriendMessage addFriendMessage1 = new ImAddFriendMessage();
                    addFriendMessage1.setUid(friendId);
                    addFriendMessage1.setSendId(uid);
                    addFriendMessage1.setMessage(message);
                    addFriendMessage1.setState(1);
                    imAddFriendMessageService.save(addFriendMessage1);
                    return RE.fail("success");
                }
            }else if(friend.getState().equals("3")){
                return RE.fail("error");
            }
        }else {
            if(ImAddFriendMessage!=null){
                if (ImAddFriendMessage.getState().equals(1)){
                    return RE.fail("error");
                }else {
                    imAddFriendMessageService.againRequest(message,uid,friendId);
                    return RE.fail("success");
                }
            }else {
                ImAddFriendMessage addFriendMessage1 = new ImAddFriendMessage();
                addFriendMessage1.setUid(friendId);
                addFriendMessage1.setSendId(uid);
                addFriendMessage1.setMessage(message);
                addFriendMessage1.setState(1);
                imAddFriendMessageService.save(addFriendMessage1);
                return RE.fail("success");
            }
        }
        return RE.fail("success");
    }

    @Override
    public RE FriendRequest(Integer uid) {
        List<ImAddFriendMessage> addFriendMessages = imAddFriendMessageService.ByUidAndState(uid);
        List<FriendListDto> friendListDtos = new ArrayList<>();
        for(ImAddFriendMessage ImAddFriendMessage : addFriendMessages){
            int uid1 = ImAddFriendMessage.getSendId();
            TjUserInfoModel tjUserInfoModel = tjUserInfoApi.findByUid(uid1);
            FriendListDto friendListDto = mapper.map(tjUserInfoModel,FriendListDto.class);
            friendListDto.setMotto(ImAddFriendMessage.getMessage());
            friendListDtos.add(friendListDto);
        }
        if (OU.isBlack(friendListDtos)){
            return RE.noData();
        }
        return RE.ok(friendListDtos);
    }

    @Override
    public RE HandlerFriendRequest(Integer uid, Integer sendId, Integer Handle) {

        imAddFriendMessageService.updateState(uid,sendId);
        ImFriend friend1 = imFriendService.findByUidAndFriendId(sendId,uid);
        if (friend1==null){
            if (Handle==1){
                ImFriend friend = new ImFriend();
                friend.setUid(uid);
                friend.setFriendId(sendId);
                imFriendService.save(friend);
                ImFriend friend11 = new ImFriend();
                friend11.setUid(sendId);
                friend11.setFriendId(uid);
                imFriendService.save(friend11);
            }
        }else {
            if(friend1.getState().equals("1")){
                if (Handle==1){
                    imFriendService.updateFriendState(sendId,uid);
                }
            }
        }
        return RE.ok();
    }


}
