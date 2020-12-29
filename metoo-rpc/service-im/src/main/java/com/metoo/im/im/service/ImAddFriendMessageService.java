package com.metoo.im.im.service;

import com.loongya.core.util.RE;
import com.metoo.im.im.dao.entity.ImAddFriendMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <p>
 * 添加好友申请记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface ImAddFriendMessageService extends IService<ImAddFriendMessage> {

    ImAddFriendMessage findByUidAndSendId(Integer uid,Integer sendId);


    List<ImAddFriendMessage> ByUidAndState(Integer uid);


    ImAddFriendMessage UidSendIdState(Integer uid,Integer friendId);

    int againRequest(String message,int uid, int sendId);

    int updateState(int uid, int sendId);

}
