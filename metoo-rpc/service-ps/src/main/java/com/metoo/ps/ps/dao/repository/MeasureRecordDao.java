package com.metoo.ps.ps.dao.repository;

import com.metoo.metoo.psychology.MeasureRecord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MeasureRecordDao extends JpaRepository<MeasureRecord,Integer> {
    List<MeasureRecord> findByUid(int uid, Pageable pageable);
    @Query(nativeQuery = true,value = "SELECT * FROM measure_record WHERE DATE_FORMAT(createtime,'%Y-%m')= :time AND uid = :uid ")
    List<MeasureRecord> findBytime(String time,Integer uid);
}

