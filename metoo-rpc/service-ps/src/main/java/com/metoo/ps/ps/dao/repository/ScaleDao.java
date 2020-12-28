package com.metoo.ps.ps.dao.repository;

import com.metoo.metoo.psychology.Scale;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ScaleDao extends JpaRepository<Scale,Integer> {
    Scale findByScaleId(Integer scaleId);
    List<Scale> findByScaleGatherId(int scaleGatherId);
    List<Scale> findAllBySpare(int spare, Pageable pageable);

    @Modifying
    @Query(value = "update scale set number=? where scale_id=?",nativeQuery = true)
    int updateNumber(int number,int scaleId);

    @Query(nativeQuery = true,value = "SELECT * FROM scale ORDER BY RAND() LIMIT 6")
    List<Scale> findScaleRand();
}
