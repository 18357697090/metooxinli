package com.metoo.im.im.service;

import com.metoo.im.im.dao.entity.ImFriend;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <p>
 * 好友列表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface ImFriendService extends IService<ImFriend> {

    List<ImFriend> findByUid(Integer uid);

    ImFriend findByUidAndFriendIdAndState(Integer uid,Integer friendId);

    ImFriend findByUidAndFriendId(Integer uid,Integer frindId);


    int updateFriendState(Integer uid,Integer friendId);


    int deleteFriendState(Integer uid,Integer friendId);


    int blackFriends(Integer state,Integer uid,Integer friendId);

}
