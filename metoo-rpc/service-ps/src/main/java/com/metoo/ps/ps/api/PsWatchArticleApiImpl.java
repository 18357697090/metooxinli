package com.metoo.ps.ps.api;

import com.loongya.core.util.RE;
import com.metoo.api.ps.PsWatchArticleApi;
import com.metoo.ps.ps.dao.entity.PsWatchArticle;
import com.metoo.ps.ps.service.PsArticleService;
import com.metoo.ps.ps.service.PsWatchArticleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户查看心理文章记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class PsWatchArticleApiImpl implements PsWatchArticleApi {

    @Autowired
    private PsWatchArticleService psWatchArticleService;

    @Autowired
    private PsArticleService psArticleService;

    @Override
    public RE watchArticle(Integer uid, Integer articleId) {

        PsWatchArticle watchArticle = psWatchArticleService.findByUid(uid);
        if (watchArticle==null){
            PsWatchArticle watchArticle1 = new PsWatchArticle();
            watchArticle1.setArticleId(articleId);
            watchArticle1.setUid(uid);
            psWatchArticleService.save(watchArticle1);
            psArticleService.updateNumber(articleId);
        }
        return RE.ok();
    }
}
