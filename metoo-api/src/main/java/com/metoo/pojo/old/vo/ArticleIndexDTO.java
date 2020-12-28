package com.metoo.pojo.old.vo;

import com.metoo.metoo.psychology.ArticleBanner;
import lombok.Data;

import java.util.List;

@Data
public class ArticleIndexDTO {
    private List<ArticleBanner> articleBanners;
    private List<com.metoo.metoo.DTO.ArticleDTO> boutiqueArticle;
    private List<com.metoo.metoo.DTO.ArticleDTO> moreArticles;

}
