package com.metoo.ps.ps.service.impl;

import com.metoo.ps.ps.dao.entity.PsWatchArticle;
import com.metoo.ps.ps.dao.mapper.PsWatchArticleMapper;
import com.metoo.ps.ps.dao.repository.PsWatchArticleRepository;
import com.metoo.ps.ps.service.PsWatchArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户查看心理文章记录表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class PsWatchArticleServiceImpl extends ServiceImpl<PsWatchArticleMapper, PsWatchArticle> implements PsWatchArticleService {

    @Autowired
    private PsWatchArticleRepository psWatchArticleRepository;

    @Override
    public PsWatchArticle findByUid(Integer uid) {
        return psWatchArticleRepository.findByUid(uid);
    }
}
