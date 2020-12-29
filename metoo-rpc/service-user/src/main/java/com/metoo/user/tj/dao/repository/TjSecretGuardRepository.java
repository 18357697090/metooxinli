package com.metoo.user.tj.dao.repository;

import com.metoo.user.tj.dao.entity.TjSecretGuard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TjSecretGuardRepository extends JpaRepository<TjSecretGuard,Integer> {

    TjSecretGuard findByUsername(String username);
}
