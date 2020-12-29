package com.metoo.ps.ps.api;

import com.loongya.core.util.RE;
import com.metoo.api.ps.PsArticleBannerApi;
import com.metoo.pojo.old.vo.ArticleDTO;
import com.metoo.pojo.old.vo.ArticleIndexDTO;
import com.metoo.ps.ps.dao.entity.PsArticle;
import com.metoo.ps.ps.service.PsArticleBannerService;
import com.metoo.ps.ps.service.PsArticleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 心理文章轮播图表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
public class PsArticleBannerApiImpl implements PsArticleBannerApi {

    @Autowired
    private PsArticleBannerService psArticleBannerService;

    @Autowired
    private PsArticleService psArticleService;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public RE index() {

        ArticleIndexDTO articleIndexDTO = new ArticleIndexDTO();
        articleIndexDTO.setArticleBanners(psArticleBannerService.findAll());
        List<PsArticle> boutiqueArticles = psArticleService.findArticleRand4();
        List<ArticleDTO> boutiqueArticle = new ArrayList<>();
        for (PsArticle article : boutiqueArticles) {
            ArticleDTO articleDTO = mapper.map(article,ArticleDTO.class);
            boutiqueArticle.add(articleDTO);
        }

        Pageable pageable = PageRequest.of(0,4, Sort.Direction.DESC,"sort");
        List<PsArticle>  moreArticles = psArticleService.findByState(1,pageable);
        List<ArticleDTO> moreArticlesDTO = new ArrayList<>();
        for (PsArticle article : moreArticles){
            ArticleDTO articleDTO = mapper.map(article,ArticleDTO.class);
            moreArticlesDTO.add(articleDTO);
        }
        articleIndexDTO.setMoreArticles(moreArticlesDTO);
        articleIndexDTO.setBoutiqueArticle(boutiqueArticle);


        return RE.ok(articleIndexDTO);
    }
}
