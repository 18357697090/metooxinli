package com.metoo.xy.xy.dao.repository;

import com.metoo.xy.xy.dao.entity.XyRace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XyRaceRepository extends JpaRepository<XyRace,Integer> {
    XyRace findByRaceId(Integer raceId);
}