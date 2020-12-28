package com.metoo.ps.ps.dao.repository;

import com.metoo.metoo.psychology.WatchArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchArticleDao extends JpaRepository<WatchArticle,Integer> {
    WatchArticle findByUid(Integer uid);
}
