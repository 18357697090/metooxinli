package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsMeasureRecord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PsMeasureRecordRepository extends JpaRepository<PsMeasureRecord,Integer> {
    List<PsMeasureRecord> findByUid(int uid, Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM ps_measure_record WHERE DATE_FORMAT(createtime,'%Y-%m')= :time AND uid = :uid ")
    List<PsMeasureRecord> findBytime(String time,Integer uid);
}

