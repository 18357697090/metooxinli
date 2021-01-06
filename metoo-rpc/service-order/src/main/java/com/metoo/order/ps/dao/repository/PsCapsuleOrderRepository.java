package com.metoo.order.ps.dao.repository;

import com.metoo.order.ps.dao.entity.PsCapsuleOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsCapsuleOrderRepository extends JpaRepository<PsCapsuleOrder,Integer> {
    PsCapsuleOrder findByUidAndCapsuleId(int uid,int capsuleId);
}
