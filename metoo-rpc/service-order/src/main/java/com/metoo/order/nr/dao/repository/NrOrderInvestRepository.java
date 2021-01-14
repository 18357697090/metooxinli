package com.metoo.order.nr.dao.repository;

import com.metoo.order.nr.dao.entity.NrGoods;
import com.metoo.order.nr.dao.entity.NrOrderInvest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NrOrderInvestRepository extends JpaRepository<NrOrderInvest,Integer> {

    NrOrderInvest findFirstByOrderNo(String orderNo);

}
