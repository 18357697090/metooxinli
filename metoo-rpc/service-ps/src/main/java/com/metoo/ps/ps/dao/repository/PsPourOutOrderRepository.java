package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsConsultOrder;
import com.metoo.ps.ps.dao.entity.PsPourOutOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsPourOutOrderRepository extends JpaRepository<PsPourOutOrder,Integer> {
}
