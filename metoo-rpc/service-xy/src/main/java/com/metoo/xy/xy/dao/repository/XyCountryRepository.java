package com.metoo.xy.xy.dao.repository;

import com.metoo.xy.xy.dao.entity.XyCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface XyCountryRepository extends JpaRepository<XyCountry,Integer> {
    XyCountry findByCountryId(Integer countryId);

    @Query(nativeQuery = true,value = "select * from xy_country where race_id=?1 and state=1")
    List<XyCountry> findByRaceId(Integer raceId);

    XyCountry findByName(String name);
}

