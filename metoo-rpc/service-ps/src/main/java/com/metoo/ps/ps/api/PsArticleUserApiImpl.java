package com.metoo.ps.ps.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsArticleUserApi;
import com.metoo.ps.ps.dao.entity.PsArticleUser;
import com.metoo.ps.ps.service.PsArticleService;
import com.metoo.ps.ps.service.PsArticleUserService;
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
public class PsArticleUserApiImpl implements PsArticleUserApi {

    @Autowired
    private PsArticleUserService psArticleUserService;

    @Autowired
    private PsArticleService psArticleService;

    @Override
    public RE watchArticle(Integer userId, Integer articleId) {
        PsArticleUser watchArticle = psArticleUserService.findByUid(userId);
        if (OU.isBlack(watchArticle)){
            PsArticleUser pojo = new PsArticleUser(null, articleId, userId);
            psArticleUserService.save(pojo);
            psArticleService.updateNumber(articleId);
        }
        return RE.ok();
    }
}
