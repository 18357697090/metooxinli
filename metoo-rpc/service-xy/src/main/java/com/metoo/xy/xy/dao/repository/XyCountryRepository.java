package com.metoo.xy.xy.dao.repository;

import com.metoo.xy.xy.dao.entity.XyCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface XyCountryRepository extends JpaRepository<XyCountry,Integer> {

    XyCountry findByName(String name);
}

