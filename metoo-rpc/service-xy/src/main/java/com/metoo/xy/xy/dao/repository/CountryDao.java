package com.metoo.xy.xy.dao.repository;

import com.metoo.metoo.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryDao extends JpaRepository<Country,Integer> {
    Country findByCountryId(Integer countryId);

    @Query(nativeQuery = true,value = "select * from country where race_id=?1 and state=1")
    List<Country> findByRaceId(Integer raceId);

    Country findByName(String name);
}

