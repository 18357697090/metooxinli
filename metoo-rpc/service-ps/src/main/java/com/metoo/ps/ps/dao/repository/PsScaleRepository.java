package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsScale;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface PsScaleRepository extends JpaRepository<PsScale,Integer> {
    PsScale findByScaleId(Integer scaleId);

    List<PsScale> findByScaleGatherId(int scaleGatherId);

    List<PsScale> findAllByState(int state, Pageable pageable);

    @Modifying
    @Query(value = "update ps_scale set `number`=`number` + 1 where scale_id=?",nativeQuery = true)
    Integer updateNumber(Integer scaleId);

    @Query(nativeQuery = true,value = "SELECT * FROM ps_scale ORDER BY RAND() LIMIT 6")
    List<PsScale> findScaleRand();
}
