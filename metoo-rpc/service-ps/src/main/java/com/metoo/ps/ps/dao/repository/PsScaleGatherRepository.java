package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsScaleGather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PsScaleGatherRepository extends JpaRepository<PsScaleGather,Integer> {

    PsScaleGather findByScaleGatherId(int ScaleGatherId);

    @Query(value = "FROM PsScaleGather WHERE scaleGatherId>10")
    List<PsScaleGather> findByScaleGatherIdAll();
}
