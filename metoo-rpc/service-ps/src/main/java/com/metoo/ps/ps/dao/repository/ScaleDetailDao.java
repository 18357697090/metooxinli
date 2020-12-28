package com.metoo.ps.ps.dao.repository;

import com.metoo.metoo.psychology.ScaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScaleDetailDao extends JpaRepository<ScaleDetail,Integer> {

    ScaleDetail findByScaleId(int scaleId);

}
