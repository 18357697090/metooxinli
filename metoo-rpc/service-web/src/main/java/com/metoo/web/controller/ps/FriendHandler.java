package com.metoo.web.controller.ps;


import com.metoo.metoo.RwAndShopDTO.FriendListDto;
import com.metoo.metoo.entity.Friend;
import com.metoo.metoo.entity.UserInfo;
import com.metoo.metoo.entity1.AddFriendMessage;
import com.metoo.metoo.entity1.UserMessage;
import com.metoo.metoo.entity1.saveUserMessage;
import com.metoo.metoo.repository.FriendDao;
import com.metoo.metoo.repository.UserInfoDao;
import com.metoo.metoo.repository1.AddFriendMessageDao;
import com.metoo.metoo.repository1.UserMessageDao;
import com.metoo.metoo.repository1.saveUserMessageDao;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendHandler {
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private Mapper mapper;
    @Autowired
    private AddFriendMessageDao addFriendMessageDao;
    @Autowired
    private UserMessageDao userMessageDao;
    @Autowired
    private saveUserMessageDao saveUserMessageDao;



    //取离线消息
    @GetMapping("/offlineMessage")
    public List<UserMessage> offlineMessage(@RequestHeader("UID")Integer uid){
        return userMessageDao.uid(uid);
    }

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

    //搜索人，添加好友
    @GetMapping("/findFriend")
    public List<FriendListDto> fendFriend(@RequestHeader("UID")Integer uid,String name){
        List<UserInfo> userInfos = userInfoDao.findByNameLike("%"+name+"%");
        List<FriendListDto> friendListDtos = new ArrayList<>();
        for (UserInfo userInfo : userInfos){
            if (!uid.equals(userInfo.getUid())){
                FriendListDto friendListDto = mapper.map(userInfo,FriendListDto.class);
                friendListDtos.add(friendListDto);
            }
        }
        return friendListDtos;
    }

    //添加好友请求
    @PostMapping("/AddFriend")
    public String AddFriend(@RequestHeader("UID")Integer uid,@RequestParam(value = "friendId") Integer friendId,
                            @RequestParam(value = "message")String message){
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

    //查看聊天记录
    @GetMapping("/chatRecord")
    public List<saveUserMessage> chatRecord(@RequestHeader("UID")Integer uid,Integer sendId){
        return saveUserMessageDao.findByUidAndSendId(uid,sendId);
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
