package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsScaleOptions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsScaleOptionsRepository extends JpaRepository<PsScaleOptions,Integer>{
    PsScaleOptions findByScaleId(int scaleId);

}
