package com.metoo.order.nr.dao.repository;

import com.metoo.order.nr.dao.entity.NrGoods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NrGoodsRepository extends JpaRepository<NrGoods,Integer> {

    NrGoods findByType(Integer type);
}
