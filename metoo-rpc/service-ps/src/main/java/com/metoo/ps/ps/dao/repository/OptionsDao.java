package com.metoo.ps.ps.dao.repository;

import com.metoo.metoo.psychology.Options;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionsDao extends JpaRepository<Options,Integer>{
    Options findByScaleId(int scaleId);

}
