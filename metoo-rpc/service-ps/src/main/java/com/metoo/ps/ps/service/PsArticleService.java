package com.metoo.ps.ps.service;

import com.metoo.ps.ps.dao.entity.PsArticle;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p>
 * 心理文章表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsArticleService extends IService<PsArticle> {

    List<PsArticle> findArticleRand4();

    List<PsArticle> findByState(Integer i, Pageable pageable);

    PsArticle findByArticleId(Integer articleId);
}
