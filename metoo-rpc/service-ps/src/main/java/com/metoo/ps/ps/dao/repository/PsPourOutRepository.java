package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsPourOut;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PsPourOutRepository extends JpaRepository<PsPourOut,Integer> {
    List<PsPourOut> findByOnLine(Integer onLine, Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM ps_pour_out ORDER BY RAND() LIMIT 6")
    List<PsPourOut> findPourOutRand();
}
