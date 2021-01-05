package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsUserAndMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface PsUserAndMeasureRepository extends JpaRepository<PsUserAndMeasure,Integer> {
    PsUserAndMeasure findByUidAndScaleId(int uid,int scaleId);

    @Modifying
    @Query(value = "update ps_user_and_measure set state=2 where uid=? and scale_id=?",nativeQuery = true)
    int updateMeasure(int uid,int scaleId);

    @Modifying
    @Query(value = "update ps_user_and_measure set count=? where uid=? and scale_id=?",nativeQuery = true)
    int updateCount(int count,int uid,int scaleId);
}
