package com.metoo.web.controller.ps;

import com.metoo.metoo.DTO.ArticleDTO;
import com.metoo.metoo.DTO.ArticleIndexDTO;
import com.metoo.metoo.psychology.Article;
import com.metoo.metoo.psychology.WatchArticle;
import com.metoo.metoo.psychologyDao.ArticleBannerDao;
import com.metoo.metoo.psychologyDao.ArticleDao;
import com.metoo.metoo.psychologyDao.WatchArticleDao;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/article")
public class PsychologyArticleHandler {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private Mapper mapper;
    @Autowired
    private WatchArticleDao watchArticleDao;
    @Autowired
    private ArticleBannerDao articleBannerDao;



    @GetMapping("/more")
    public List<ArticleDTO> more(Integer page){
        Pageable pageable = PageRequest.of(page,5, Sort.Direction.DESC,"sort");
        List<Article>  articles = articleDao.findByState(1,pageable);
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        for (Article article : articles){
            ArticleDTO articleDTO = mapper.map(article,ArticleDTO.class);
            articleDTOS.add(articleDTO);
        }
        return articleDTOS;
    }

    @GetMapping("/content")
    public String content(Integer articleId){
        return articleDao.findByArticleId(articleId).getContent();
    }

    @GetMapping("/watchArticle")
    public void watchArticle(@RequestHeader("UID")Integer uid,Integer articleId){
        WatchArticle watchArticle = watchArticleDao.findByUid(uid);
        if (watchArticle==null){
            WatchArticle watchArticle1 = new WatchArticle();
            watchArticle1.setArticleId(articleId);
            watchArticle1.setUid(uid);
            watchArticleDao.save(watchArticle1);
            articleDao.updateNumber(articleId);
        }
    }

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
