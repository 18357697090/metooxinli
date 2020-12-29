package com.metoo.im.im.dao.repository;

import com.metoo.im.im.dao.entity.ImFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ImFriendRepository extends JpaRepository<ImFriend,Integer> {
    List<ImFriend> findByUid(Integer uid);
    @Query(nativeQuery = true,value = "select * from im_friend where uid=?1 and friend_id=?2 and state=1")
    ImFriend findByUidAndFriendIdAndState(Integer uid,Integer friendId);

    ImFriend findByUidAndFriendId(Integer uid,Integer frindId);

    @Modifying
    @Query(value = "update im_friend set state=1 where uid=?1 and friend_id=?2",nativeQuery = true)
    int updateFriendState(Integer uid,Integer friendId);

    @Modifying
    @Query(value = "update im_friend set state=2 where uid=?1 and friend_id=?2",nativeQuery = true)
    int deleteFriendState(Integer uid,Integer friendId);

    @Modifying
    @Query(value = "update im_friend set state=?1 where uid=?2 and friend_id=?3",nativeQuery = true)
    int blackFriends(Integer state,Integer uid,Integer friendId);
}
