package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsConsult;
import com.metoo.ps.ps.dao.entity.PsConsultOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PsConsultOrderRepository extends JpaRepository<PsConsultOrder,Integer> {
}
