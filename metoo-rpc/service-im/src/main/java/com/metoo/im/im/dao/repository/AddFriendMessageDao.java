package com.metoo.im.im.dao.repository;

import com.metoo.metoo.entity1.AddFriendMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface AddFriendMessageDao extends JpaRepository<AddFriendMessage,Integer> {

    @Query(value = "SELECT * FROM add_friend_message WHERE uid=?1 AND send_id=?2",nativeQuery = true)
    AddFriendMessage findByUidAndSendId(Integer uid,Integer sendId);

    @Query(value = "SELECT * FROM add_friend_message WHERE uid=? AND state=1",nativeQuery = true)
    List<AddFriendMessage> ByUidAndState(Integer uid);

    @Query(value = "SELECT * FROM add_friend_message WHERE uid=? AND send_id AND state=1",nativeQuery = true)
    AddFriendMessage UidSendIdState(Integer uid,Integer friendId);

    @Modifying
    @Query(value = "update add_friend_message set state = 1 , message=?1 where uid=?2 and send_id=?3",nativeQuery = true)
    int againRequest(String message,int uid, int sendId);

    @Modifying
    @Query(value = "update add_friend_message set state = 0 where uid=? and send_id=?",nativeQuery = true)
    int updateState(int uid, int sendId);



}
