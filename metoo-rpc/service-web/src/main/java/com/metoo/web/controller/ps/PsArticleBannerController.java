package com.metoo.web.controller.ps;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 心理文章轮播图表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/ps-article-banner")
public class PsArticleBannerController {

    @GetMapping("/index")
    public ArticleIndexDTO index(){
        ArticleIndexDTO articleIndexDTO = new ArticleIndexDTO();
        articleIndexDTO.setArticleBanners(articleBannerDao.findAll());
        List<Article> boutiqueArticles = articleDao.findArticleRand4();
        List<ArticleDTO> boutiqueArticle = new ArrayList<>();
        for (Article article : boutiqueArticles) {
            ArticleDTO articleDTO = mapper.map(article,ArticleDTO.class);
            boutiqueArticle.add(articleDTO);
        }

        Pageable pageable = PageRequest.of(0,4, Sort.Direction.DESC,"sort");
        List<Article>  moreArticles = articleDao.findByState(1,pageable);
        List<ArticleDTO> moreArticlesDTO = new ArrayList<>();
        for (Article article : moreArticles){
            ArticleDTO articleDTO = mapper.map(article,ArticleDTO.class);
            moreArticlesDTO.add(articleDTO);
        }
        articleIndexDTO.setMoreArticles(moreArticlesDTO);
        articleIndexDTO.setBoutiqueArticle(boutiqueArticle);
        return articleIndexDTO;
    }

}
