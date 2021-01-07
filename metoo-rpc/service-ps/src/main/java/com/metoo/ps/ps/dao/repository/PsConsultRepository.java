package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsConsult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PsConsultRepository extends JpaRepository<PsConsult,Integer> {
    List<PsConsult> findByOnLine(Integer onLine, Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM ps_psychology_consult ORDER BY RAND() LIMIT 6")
    List<PsConsult> findPsConsultRand();
}
