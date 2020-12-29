package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsArticle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface PsArticleRepository extends JpaRepository<PsArticle,Integer> {
    PsArticle findByArticleId(Integer articleId);

    List<PsArticle> findByState(Integer state, Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM ps_article ORDER BY RAND() LIMIT 6")
    List<PsArticle> findArticleRand();

    @Query(nativeQuery = true,value = "SELECT * FROM ps_article ORDER BY RAND() LIMIT 4")
    List<PsArticle> findArticleRand4();

    @Modifying
    @Query(value = "update ps_article set number = number+1 where article_id=?1",nativeQuery = true)
    int updateNumber(Integer articleId);

}
