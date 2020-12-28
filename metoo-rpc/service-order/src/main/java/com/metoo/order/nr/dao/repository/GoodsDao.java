package com.metoo.order.nr.dao.repository;

import com.metoo.metoo.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsDao extends JpaRepository<Goods,Integer> {

    Goods findByType(Integer type);
}
