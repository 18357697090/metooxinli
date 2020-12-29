package com.metoo.im.im.service;

import com.loongya.core.util.RE;
import com.metoo.im.im.dao.entity.ImAddFriendMessage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 添加好友申请记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface ImAddFriendMessageService extends IService<ImAddFriendMessage> {
    RE AddFriend(Integer uid,Integer friendId,String message);
    RE FriendRequest(Integer uid);
    RE HandlerFriendRequest(Integer uid, Integer sendId, Integer handle);

}
