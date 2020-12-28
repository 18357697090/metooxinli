package com.metoo.web.controller.im;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 好友列表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/im/im-friend")
public class ImFriendController {


    //好友列表
    @GetMapping("/friendList")
    public List<FriendListDto> friendList(@RequestHeader("UID")Integer uid){
        List<Friend> friends = friendDao.findByUid(uid);
        List<FriendListDto> friendListDtos = new ArrayList<>();
        for (Friend friend : friends){
            if(friend.getState()==1||friend.getState()==3){
                int fid = friend.getFriendId();
                UserInfo userInfo = userInfoDao.findByUid(fid);
                FriendListDto friendListDto = mapper.map(userInfo,FriendListDto.class);
                friendListDto.setState(friend.getState());
                friendListDtos.add(friendListDto);
            }
        }
        return friendListDtos;
    }

    @GetMapping("/deleteFriend")
    public String deleteFriend(@RequestHeader("UID")Integer uid,Integer friendId){
        friendDao.deleteFriendState(uid,friendId);
        return "success";
    }


    @GetMapping("/blackFriends")
    public String blackFriends(@RequestHeader("UID")Integer uid,Integer friendId){
        Friend friend = friendDao.findByUidAndFriendId(uid,friendId);
        if (friend.getState()==1){
            Integer i =friendDao.blackFriends(3,uid,friendId);
            if(i==1){
                return "success";
            }else {
                return "error";
            }
        }else if (friend.getState()==3){
            Integer i =friendDao.blackFriends(1,uid,friendId);
            if(i==1){
                return "success";
            }else {
                return "error";
            }
        }else {
            return "error";
        }
    }

    @GetMapping("/findBlackFriends")
    public Integer findBlackFriends(@RequestHeader("UID")Integer uid,Integer friendId){
        return friendDao.findByUidAndFriendId(uid,friendId).getState();
    }

}
