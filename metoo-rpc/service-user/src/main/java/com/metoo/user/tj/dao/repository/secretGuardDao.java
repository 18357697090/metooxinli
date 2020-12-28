package com.metoo.user.tj.dao.repository;

import com.metoo.metoo.entity.secretGuard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface secretGuardDao extends JpaRepository<secretGuard,Integer> {

    secretGuard findByUsername(String username);
}
