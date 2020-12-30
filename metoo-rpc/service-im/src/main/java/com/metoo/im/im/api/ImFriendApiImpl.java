package com.metoo.im.im.api;


import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.im.ImFriendApi;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.im.im.dao.entity.ImFriend;
import com.metoo.im.im.service.ImFriendService;
import com.metoo.pojo.im.model.ImFriendModel;
import com.metoo.pojo.old.vo.FriendListDto;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 好友列表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
public class ImFriendApiImpl implements ImFriendApi {

    @DubboReference
    private TjUserInfoApi tjUserInfoApi;

    @Autowired
    private ImFriendService imFriendService;
    @Autowired
    private Mapper mapper;

    @Override
    public RE friendList(Integer uid) {
        List<ImFriend> friends = imFriendService.findByUid(uid);
        List<FriendListDto> friendListDtos = new ArrayList<>();
        for (ImFriend friend : friends){
            if(friend.getState().equals("1")||friend.getState().equals("3")){
                int fid = friend.getFriendId();
                TjUserInfoModel userInfo = tjUserInfoApi.findByUid(fid);
                FriendListDto friendListDto = mapper.map(userInfo,FriendListDto.class);
                friendListDto.setState(Integer.parseInt(friend.getState()));
                friendListDtos.add(friendListDto);
            }
        }
        if(OU.isBlack(friendListDtos)){
            return RE.noData();
        }
        return RE.ok(friendListDtos);
    }

    @Override
    public RE deleteFriend(Integer uid, Integer friendId) {
        imFriendService.deleteFriendState(uid,friendId);
        return RE.ok();
    }

    @Override
    public RE blackFriends(Integer uid, Integer friendId) {
        ImFriend friend = imFriendService.findByUidAndFriendId(uid,friendId);
        if (friend.getState().equals("1")){
            Integer i =imFriendService.blackFriends(3,uid,friendId);
            if(i==1){
                return RE.serviceFail("error");
            }else {
                return RE.serviceFail("error");
            }
        }else if (friend.getState().equals("3")){
            Integer i =imFriendService.blackFriends(1,uid,friendId);
            if(i==1){
                return RE.serviceFail("success");
            }else {
                return RE.serviceFail("error");
            }
        }else {
            return RE.serviceFail("error");
        }
    }

    @Override
    public RE findBlackFriends(Integer uid, Integer friendId) {
        String x = imFriendService.findByUidAndFriendId(uid,friendId).getState();
        if (OU.isBlack(x)){
            return RE.noData();
        }
        return RE.ok(x);
    }

    @Override
    public ImFriendModel findByUidAndFriendId(Integer uid, Integer firendId) {
        ImFriend imFriend = imFriendService.findByUidAndFriendId(uid,firendId);
        return mapper.map(imFriend,ImFriendModel.class);
    }
}
