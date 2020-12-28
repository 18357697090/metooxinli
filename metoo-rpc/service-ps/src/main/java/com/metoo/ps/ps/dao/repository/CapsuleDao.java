package com.metoo.ps.ps.dao.repository;

import com.metoo.metoo.psychology.Capsule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CapsuleDao extends JpaRepository<Capsule,Integer> {
    Capsule findByCapsuleId(Integer capsuleId);
    Integer countByType(Integer type);

//    @Query(nativeQuery = true,value = "SELECT * FROM Capsule WHERE attribute=1 ORDER BY id DESC LIMIT ?,7")
//    List<Capsule> findCapsules(Integer page);


    @Modifying
    @Query(value = "update Capsule set state=0 where capsule_id=?",nativeQuery = true)
    int updataState(Integer capsuleId);

    @Modifying
    @Query(value = "update Capsule set attribute=? where capsule_id=?",nativeQuery = true)
    int updataAttribute(Integer attribute,Integer capsuleId);


    @Query(nativeQuery = true,value = "SELECT capsule_id,creation_time,be_watched,prices,title,uid,attribute FROM Capsule WHERE uid = ? and state = 1 ORDER BY id DESC LIMIT ?,7")
    List<Object[]> findmyCapsules(Integer uid,Integer page);

    @Query(nativeQuery = true,value = "SELECT capsule_id,creation_time,be_watched,prices,title,uid FROM Capsule WHERE attribute=1 and state = 1 ORDER BY id DESC LIMIT ?,7")
    List<Object[]> findCapsules(Integer page);
//    @Query(nativeQuery = true,value = "SELECT * FROM Capsule WHERE attribute=1 ORDER BY RAND() LIMIT 4")
//    List<Capsule> findCapsule();

    @Query(nativeQuery = true,value = "SELECT capsule_id,creation_time,be_watched,prices,title,uid FROM Capsule WHERE attribute=1 and state = 1 ORDER BY RAND() LIMIT 4")
    List<Object[]> findCapsule();


}
