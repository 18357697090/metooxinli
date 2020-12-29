package com.metoo.ps.ps.service.impl;

import com.metoo.ps.ps.dao.entity.PsArticle;
import com.metoo.ps.ps.dao.mapper.PsArticleMapper;
import com.metoo.ps.ps.dao.repository.PsArticleRepository;
import com.metoo.ps.ps.service.PsArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 心理文章表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class PsArticleServiceImpl extends ServiceImpl<PsArticleMapper, PsArticle> implements PsArticleService {

    @Autowired
    private PsArticleRepository psArticleRepository;

    @Override
    public List<PsArticle> findArticleRand4() {
        return psArticleRepository.findArticleRand4();
    }

    @Override
    public List<PsArticle> findByState(Integer i, Pageable pageable) {
        return psArticleRepository.findByState(i, pageable);
    }

    @Override
    public PsArticle findByArticleId(Integer articleId) {
        return psArticleRepository.findByArticleId(articleId);
    }
}
