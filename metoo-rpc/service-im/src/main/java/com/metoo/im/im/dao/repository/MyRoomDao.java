package com.metoo.im.im.dao.repository;

import com.metoo.metoo.entity.MyRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyRoomDao extends JpaRepository<MyRoom,Integer> {
    @Query(nativeQuery = true,value = "select * from my_room where my_room_id=?1 and state=1 and is_host=1")
    MyRoom findByMyRoomIdAndIsHost(Integer myCityId);

    @Query(nativeQuery = true,value = "select * from my_room where uid=?1 and state=1 and type=2")
    List<MyRoom> findBMyCityList(Integer uid);

    @Query(nativeQuery = true,value = "select * from my_room where uid=?1 and state=1")
    List<MyRoom> findByUidAndState(Integer uid);

    @Query(nativeQuery = true,value = "select * from my_room where my_room_id=?1")
    List<MyRoom> findByMyRoomId(Integer myRoomId);

    @Query(nativeQuery = true,value = "select * from my_room where uid=?1 and my_room_id=?2")
    MyRoom findByUidAndMyRoomId(Integer uid,Integer myRoomId);

    List<MyRoom> findByUid(Integer uid);
}
