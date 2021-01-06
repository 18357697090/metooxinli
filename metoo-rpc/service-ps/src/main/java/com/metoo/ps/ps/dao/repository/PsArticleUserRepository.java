package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsArticleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsArticleUserRepository extends JpaRepository<PsArticleUser,Integer> {
    PsArticleUser findByUid(Integer uid);
}
