package com.metoo.ps.ps.dao.repository;

import com.metoo.metoo.psychology.PsychologyConsult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PsychologyConsultDao extends JpaRepository<PsychologyConsult,Integer> {
    List<PsychologyConsult> findByOnLine(Integer onLine, Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM psychology_consult ORDER BY RAND() LIMIT 6")
    List<PsychologyConsult> findPsychologyConsultRand();
}
