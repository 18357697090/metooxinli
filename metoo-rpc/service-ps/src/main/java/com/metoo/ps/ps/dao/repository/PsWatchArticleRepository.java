package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsWatchArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsWatchArticleRepository extends JpaRepository<PsWatchArticle,Integer> {
    PsWatchArticle findByUid(Integer uid);
}
