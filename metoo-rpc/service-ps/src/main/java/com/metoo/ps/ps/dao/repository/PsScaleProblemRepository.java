package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsScaleProblem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PsScaleProblemRepository extends JpaRepository<PsScaleProblem,Integer> {
    List<PsScaleProblem> findByScaleId(Integer scaleId);
}
