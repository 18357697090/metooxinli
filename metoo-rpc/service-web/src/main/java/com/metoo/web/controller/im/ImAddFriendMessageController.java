package com.metoo.web.controller.im;


import com.loongya.core.util.RE;
import com.metoo.api.im.ImAddFriendMessageApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * <p>
 * 添加好友申请记录表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/im/im-add-friend-message")
public class ImAddFriendMessageController {

    @DubboReference
    private ImAddFriendMessageApi imAddFriendMessageApi;

    //添加好友请求
    @PostMapping("/AddFriend")
    public RE AddFriend(@RequestHeader("UID")Integer uid, @RequestParam(value = "friendId") Integer friendId,
                        @RequestParam(value = "message")String message){
        return imAddFriendMessageApi.AddFriend(uid,friendId,message);





        Friend friend = friendDao.findByUidAndFriendId(uid,friendId);
        if (friend!=null){
            if(friend.getState()==1){
                return "exist";
            }else if (friend.getState()==2){
                AddFriendMessage addFriendMessage= addFriendMessageDao.findByUidAndSendId(friendId,uid);
                System.out.println(addFriendMessage);
                if(addFriendMessage!=null){
                    if (addFriendMessage.getState().equals(1)){
                        return "error";
                    }else {
                        addFriendMessageDao.againRequest(message,friendId,uid);
                        return "success";
                    }
                }else {
                    AddFriendMessage addFriendMessage1 = new AddFriendMessage();
                    addFriendMessage1.setUid(friendId);
                    addFriendMessage1.setSendId(uid);
                    addFriendMessage1.setMessage(message);
                    addFriendMessage1.setState(1);
                    addFriendMessageDao.save(addFriendMessage1);
                    return "success";
                }
            }else if(friend.getState()==3){
                return "blackFriends";
            }
        }else {
            AddFriendMessage addFriendMessage= addFriendMessageDao.findByUidAndSendId(uid,friendId);
            if(addFriendMessage!=null){
                if (addFriendMessage.getState().equals(1)){
                    return "error";
                }else {
                    addFriendMessageDao.againRequest(message,uid,friendId);
                    return "success";
                }
            }else {
                AddFriendMessage addFriendMessage1 = new AddFriendMessage();
                addFriendMessage1.setUid(friendId);
                addFriendMessage1.setSendId(uid);
                addFriendMessage1.setMessage(message);
                addFriendMessage1.setState(1);
                addFriendMessageDao.save(addFriendMessage1);
                return "success";
            }
        }
        return "success";
    }

    //查看好友申请
    @GetMapping("/FriendRequest")
    public List<FriendListDto> FriendRequest(@RequestHeader("UID")Integer uid){
        List<AddFriendMessage> addFriendMessages = addFriendMessageDao.ByUidAndState(uid);
        List<FriendListDto> friendListDtos = new ArrayList<>();
        for(AddFriendMessage addFriendMessage : addFriendMessages){
            int uid1 = addFriendMessage.getSendId();
            UserInfo userInfo = userInfoDao.findByUid(uid1);
            FriendListDto friendListDto = mapper.map(userInfo,FriendListDto.class);
            friendListDto.setMotto(addFriendMessage.getMessage());
            friendListDtos.add(friendListDto);
        }
        return friendListDtos;
    }


    //处理好友请求
    @GetMapping("/HandleFriendRequest")//handle 1表示同意，0表示拒绝
    public String HandlerFriendRequest(@RequestHeader("UID")Integer uid,Integer sendId,Integer Handle){
        addFriendMessageDao.updateState(uid,sendId);
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

        return "success";
    }

}
