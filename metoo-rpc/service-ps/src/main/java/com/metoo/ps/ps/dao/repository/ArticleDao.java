package com.metoo.ps.ps.dao.repository;

import com.metoo.metoo.psychology.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ArticleDao extends JpaRepository<Article,Integer> {
    Article findByArticleId(Integer articleId);

    List<Article> findByState(Integer state, Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM article ORDER BY RAND() LIMIT 6")
    List<Article> findArticleRand();

    @Query(nativeQuery = true,value = "SELECT * FROM article ORDER BY RAND() LIMIT 4")
    List<Article> findArticleRand4();

    @Modifying
    @Query(value = "update article set number = number+1 where article_id=?1",nativeQuery = true)
    int updateNumber(Integer articleId);

}
