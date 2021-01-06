package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsScaleMeasureRecord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PsScaleMeasureRecordRepository extends JpaRepository<PsScaleMeasureRecord,Integer> {
    List<PsScaleMeasureRecord> findByUid(int uid, Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM ps_scale_measure_record WHERE DATE_FORMAT(createtime,'%Y-%m')= :time AND uid = :uid ")
    List<PsScaleMeasureRecord> findBytime(String time, Integer uid);

    @Modifying
    @Query(value = "update ps_scale_measure_record set state=2 where uid=? and scale_id=?",nativeQuery = true)
    int updateMeasure(int uid,int scaleId);

    @Modifying
    @Query(value = "update ps_scale_measure_record set count=? where uid=? and scale_id=?",nativeQuery = true)
    int updateCount(int count,int uid,int scaleId);

    PsScaleMeasureRecord findByUidAndScaleId(Integer uid, Integer scaleId);
    PsScaleMeasureRecord findFirstByUidAndScaleIdOrderByCreateTimeDesc(Integer uid, Integer scaleId);
}

