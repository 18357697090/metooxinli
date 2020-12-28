package com.metoo.order.ps.dao.repository;

import com.metoo.metoo.psychology.UserBuyCapsule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBuyCapsuleDao extends JpaRepository<UserBuyCapsule,Integer> {
    UserBuyCapsule findByUidAndCapsuleId(int uid,int capsuleId);
}
