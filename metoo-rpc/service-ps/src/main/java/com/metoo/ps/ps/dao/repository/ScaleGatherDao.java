package com.metoo.ps.ps.dao.repository;

import com.metoo.metoo.psychology.ScaleGather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScaleGatherDao extends JpaRepository<ScaleGather,Integer> {
    ScaleGather findByScaleGatherId(int ScaleGatherId);

    @Query(value = "FROM ScaleGather WHERE scaleGatherId>10")
    List<ScaleGather> findByScaleGatherIdAll();
}
