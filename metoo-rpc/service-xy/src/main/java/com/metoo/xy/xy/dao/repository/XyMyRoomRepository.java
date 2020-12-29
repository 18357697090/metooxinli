package com.metoo.xy.xy.dao.repository;

import com.metoo.xy.xy.dao.entity.XyMyRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface XyMyRoomRepository extends JpaRepository<XyMyRoom,Integer> {
    @Query(nativeQuery = true,value = "select * from xy_my_room where my_room_id=?1 and state=1 and is_host=1")
    XyMyRoom findByMyRoomIdAndIsHost(Integer myCityId);

    @Query(nativeQuery = true,value = "select * from xy_my_room where uid=?1 and state=1 and type=2")
    List<XyMyRoom> findBMyCityList(Integer uid);

    @Query(nativeQuery = true,value = "select * from xy_my_room where uid=?1 and state=1")
    List<XyMyRoom> findByUidAndState(Integer uid);

    @Query(nativeQuery = true,value = "select * from xy_my_room where my_room_id=?1")
    List<XyMyRoom> findByMyRoomId(Integer myRoomId);

    @Query(nativeQuery = true,value = "select * from xy_my_room where uid=?1 and my_room_id=?2")
    XyMyRoom findByUidAndMyRoomId(Integer uid,Integer myRoomId);

    List<XyMyRoom> findByUid(Integer uid);
}
