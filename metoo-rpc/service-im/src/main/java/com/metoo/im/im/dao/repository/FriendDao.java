package com.metoo.im.im.dao.repository;

import com.metoo.metoo.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface FriendDao extends JpaRepository<Friend,Integer> {
    List<Friend> findByUid(Integer uid);
    @Query(nativeQuery = true,value = "select * from friend where uid=?1 and friend_id=?2 and state=1")
    Friend findByUidAndFriendIdAndState(Integer uid,Integer friendId);

    Friend findByUidAndFriendId(Integer uid,Integer frindId);

    @Modifying
    @Query(value = "update friend set state=1 where uid=?1 and friend_id=?2",nativeQuery = true)
    int updateFriendState(Integer uid,Integer friendId);

    @Modifying
    @Query(value = "update friend set state=2 where uid=?1 and friend_id=?2",nativeQuery = true)
    int deleteFriendState(Integer uid,Integer friendId);

    @Modifying
    @Query(value = "update friend set state=?1 where uid=?2 and friend_id=?3",nativeQuery = true)
    int blackFriends(Integer state,Integer uid,Integer friendId);
}
