package com.metoo.ps.ps.dao.repository;

import com.metoo.metoo.psychology.PourOut;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PourOutDao extends JpaRepository<PourOut,Integer> {
    List<PourOut> findByOnLine(Integer onLine, Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM pour_out ORDER BY RAND() LIMIT 6")
    List<PourOut> findPourOutRand();
}
