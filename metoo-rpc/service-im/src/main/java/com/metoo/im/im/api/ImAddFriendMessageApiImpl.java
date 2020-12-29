package com.metoo.im.im.api;


import com.loongya.core.util.RE;
import com.metoo.api.im.ImAddFriendMessageApi;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.im.im.service.ImAddFriendMessageService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class ImAddFriendMessageApiImpl implements ImAddFriendMessageApi {

    @DubboReference
    private TjUserInfoApi tjUserInfoApi;

    @Autowired
    private ImAddFriendMessageService imAddFriendMessageService;

    @Override
    public RE AddFriend(Integer uid, Integer friendId, String message) {
        return imAddFriendMessageService.AddFriend(uid,friendId,message);
    }

    @Override
    public RE FriendRequest(Integer uid) {
        return imAddFriendMessageService.FriendRequest(uid);
    }

    @Override
    public RE HandlerFriendRequest(Integer uid, Integer sendId, Integer handle) {
        return imAddFriendMessageService.HandlerFriendRequest(uid,sendId,handle);
    }
}
