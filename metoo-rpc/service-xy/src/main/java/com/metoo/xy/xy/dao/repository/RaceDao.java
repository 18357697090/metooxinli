package com.metoo.xy.xy.dao.repository;

import com.metoo.metoo.entity.Race;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceDao extends JpaRepository<Race,Integer> {
    Race findByRaceId(Integer raceId);
}