package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsScaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsScaleDetailRepository extends JpaRepository<PsScaleDetail,Integer> {

    PsScaleDetail findByScaleId(int scaleId);

}
