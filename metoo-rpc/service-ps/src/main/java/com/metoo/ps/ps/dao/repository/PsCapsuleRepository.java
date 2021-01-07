package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsCapsule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface PsCapsuleRepository extends JpaRepository<PsCapsule,Integer> {

    PsCapsule findByCapsuleId(Integer capsuleId);

    Integer countByType(Integer type);

    @Query(nativeQuery = true,value = "SELECT * FROM ps_capsule WHERE auth_type=1 and `state` = 0 ORDER BY RAND() LIMIT 4")
    List<PsCapsule> findCapsule();

    @Query(nativeQuery = true,value = "SELECT * FROM ps_capsule WHERE auth_type=1 and  `state` = 0 ORDER BY RAND() LIMIT ?1")
    List<PsCapsule> findAllByRand(Integer limit);

    @Modifying
    @Query(value = "update ps_capsule set read_num=read_num+1 where id= :capsuleId ",nativeQuery = true)
    void updateReadNum(Integer capsuleId);

}
