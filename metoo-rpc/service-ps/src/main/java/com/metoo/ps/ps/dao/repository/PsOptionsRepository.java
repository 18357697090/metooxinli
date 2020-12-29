package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsOptions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsOptionsRepository extends JpaRepository<PsOptions,Integer>{
    PsOptions findByScaleId(int scaleId);

}
