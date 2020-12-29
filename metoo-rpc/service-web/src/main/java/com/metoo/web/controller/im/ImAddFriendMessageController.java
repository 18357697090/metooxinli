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
    }

    //查看好友申请
    @GetMapping("/FriendRequest")
    public RE FriendRequest(@RequestHeader("UID")Integer uid){
        return imAddFriendMessageApi.FriendRequest(uid);
    }


    //处理好友请求
    @GetMapping("/HandleFriendRequest")//handle 1表示同意，0表示拒绝
    public RE HandlerFriendRequest(@RequestHeader("UID")Integer uid,Integer sendId,Integer Handle){
        return imAddFriendMessageApi.HandlerFriendRequest(uid,sendId,Handle);
    }

}
