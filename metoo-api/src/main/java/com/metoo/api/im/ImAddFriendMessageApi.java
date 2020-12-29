package com.metoo.api.im;


import com.loongya.core.util.RE;

/**
 * <p>
 * 添加好友申请记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface ImAddFriendMessageApi {

    RE AddFriend(Integer uid,Integer friendId,String message);

}
