package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsProblem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PsProblemRepository extends JpaRepository<PsProblem,Integer> {
    List<PsProblem> findByScaleId(Integer scaleId);
}
