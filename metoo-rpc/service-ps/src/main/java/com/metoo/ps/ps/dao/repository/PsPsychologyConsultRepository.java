package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsPsychologyConsult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PsPsychologyConsultRepository extends JpaRepository<PsPsychologyConsult,Integer> {
    List<PsPsychologyConsult> findByOnLine(Integer onLine, Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM ps_psychology_consult ORDER BY RAND() LIMIT 6")
    List<PsPsychologyConsult> findPsPsychologyConsultRand();
}
