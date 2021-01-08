package com.metoo.user.in.dao.repository;

import com.metoo.user.in.dao.entity.InBanner;
import com.metoo.user.in.dao.entity.InDict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InDictRepository extends JpaRepository<InDict,Integer> {

    List<InDict> findAllByPkey(String levelDict);

}
