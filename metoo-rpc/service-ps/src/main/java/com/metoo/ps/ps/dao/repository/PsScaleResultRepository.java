package com.metoo.ps.ps.dao.repository;


import com.metoo.ps.ps.dao.entity.PsScaleResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PsScaleResultRepository extends JpaRepository<PsScaleResult,Integer> {
    List<PsScaleResult> findByScaleId(Integer scaleId);
}
