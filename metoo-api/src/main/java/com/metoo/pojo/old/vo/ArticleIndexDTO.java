package com.metoo.pojo.old.vo;

import com.metoo.pojo.ps.model.PsArticleBannerModel;
import lombok.Data;

import java.util.List;

@Data
public class ArticleIndexDTO {
    private List<PsArticleBannerModel> articleBanners;
    private List<ArticleDTO> boutiqueArticle;
    private List<ArticleDTO> moreArticles;

}
