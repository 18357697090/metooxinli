package com.metoo.ps.ps.dao.repository;

import com.metoo.metoo.psychology.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemDao extends JpaRepository<Problem,Integer> {
    List<Problem> findByScaleId(Integer scaleId);
}
