package com.metoo.order.ps.dao.repository;

import com.metoo.order.ps.dao.entity.PsUserBuyCapsule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsUserBuyCapsuleRepository extends JpaRepository<PsUserBuyCapsule,Integer> {
    PsUserBuyCapsule findByUidAndCapsuleId(int uid,int capsuleId);
}
