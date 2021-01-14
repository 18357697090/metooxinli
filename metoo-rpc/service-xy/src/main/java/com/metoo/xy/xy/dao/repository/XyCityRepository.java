package com.metoo.xy.xy.dao.repository;

import com.metoo.xy.xy.dao.entity.XyCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface XyCityRepository extends JpaRepository<XyCity,Integer> {

    @Query(nativeQuery = true,value = "select * from xy_city where country_id=?1 and state=1")
    List<XyCity> findByCountryId(Integer countryId);
}
