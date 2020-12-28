package com.metoo.im.im.dao.repository;

import com.metoo.metoo.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityDao extends JpaRepository<City,Integer> {
    City findByCityId(Integer cityId);

    @Query(nativeQuery = true,value = "select * from city where country_id=?1 and state=1")
    List<City> findByCountryId(Integer countryId);
}
