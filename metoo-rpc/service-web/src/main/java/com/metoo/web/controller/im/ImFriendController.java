package com.metoo.web.controller.im;


import com.loongya.core.util.RE;
import com.metoo.api.im.ImFriendApi;
import org.apache.dubbo.config.annotation.DubboReference;
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

    @DubboReference
    private ImFriendApi imFriendApi;

    //好友列表
    @GetMapping("/friendList")
    public RE friendList(@RequestHeader("UID")Integer uid){
        return imFriendApi.friendList(uid);
    }

    @GetMapping("/deleteFriend")
    public RE deleteFriend(@RequestHeader("UID")Integer uid,Integer friendId){
        return imFriendApi.deleteFriend(uid,friendId);
    }


    @GetMapping("/blackFriends")
    public RE blackFriends(@RequestHeader("UID")Integer uid,Integer friendId){
        return imFriendApi.blackFriends(uid,friendId);
    }

    @GetMapping("/findBlackFriends")
    public RE findBlackFriends(@RequestHeader("UID")Integer uid,Integer friendId){
        return imFriendApi.findBlackFriends(uid,friendId);
    }

}
