package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsArticle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface PsArticleRepository extends JpaRepository<PsArticle,Integer> {

    PsArticle findByArticleId(Integer articleId);

    List<PsArticle> findByState(Integer state, Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM ps_article ORDER BY RAND() LIMIT ?1")
    List<PsArticle> findArticleRand(Integer count);

    @Modifying
    @Query(value = "update ps_article set number = number+1 where article_id=?1",nativeQuery = true)
    int updateNumber(Integer articleId);

    @Modifying
    @Query(value = "update ps_article set click_count = click_count+1 where article_id=?1",nativeQuery = true)
    void addClickCount(Integer articleId);
}
