package com.metoo.ps.ps.api;

import com.loongya.core.util.*;
import com.loongya.core.util.aliyun.OSSUtil;
import com.metoo.api.ps.PsArticleApi;
import com.metoo.pojo.old.vo.ArticleDTO;
import com.metoo.pojo.ps.model.PsArticleModel;
import com.metoo.pojo.ps.vo.PsArticleVo;
import com.metoo.ps.ps.dao.entity.PsArticle;
import com.metoo.ps.ps.service.PsArticleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 心理文章表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class PsArticleApiImpl implements PsArticleApi {

    @Autowired
    private PsArticleService psArticleService;

    @Override
    public RE getArticleBoutiqueList(Integer count) {
        List<PsArticle> boutiqueArticles = psArticleService.findArticleRand(count);
        if(OU.isBlack(boutiqueArticles)){
            return RE.fail(CommsEnum.NO_DATA);
        }
        return RE.ok(boutiqueArticles.stream().flatMap(e->{
            PsArticleModel model = CopyUtils.copy(e, new PsArticleModel());
            model.setPicture(OSSUtil.fillPath(model.getPicture()));
            return Stream.of(model);
        }).collect(Collectors.toList()));
    }

    @Override
    public RE getArticleBoutiqueMoreList(PsArticleVo vo) {
        Pageable pageable = PageRequest.of(vo.getPagenum()-1,vo.getPagesize(), Sort.Direction.DESC,"sort");
        List<PsArticle> articleList = psArticleService.findByState(ConstantUtil.YesOrNo.YES.getCode(),pageable);
        if(OU.isBlack(articleList)){
            return RE.noData();
        }
        return REPage.ok(vo.getPagenum(), vo.getPagesize(), null, articleList.stream().flatMap(e->{
            PsArticleModel model = CopyUtils.copy(e, new PsArticleModel());
            model.setPicture(OSSUtil.fillPath(model.getPicture()));
            return Stream.of(model);
        }).collect(Collectors.toList()));
    }

    @Override
    public RE getArticleRecommendMoreList(PsArticleVo vo) {
        Pageable pageable = PageRequest.of(vo.getPagenum()-1,vo.getPagesize(), Sort.Direction.DESC,"sort");
        List<PsArticle> articleList = psArticleService.findByState(ConstantUtil.YesOrNo.YES.getCode(),pageable);
        if(OU.isBlack(articleList)){
            return RE.noData();
        }
        return REPage.ok(vo.getPagenum(), vo.getPagesize(), null, articleList.stream().flatMap(e->{
            PsArticleModel model = CopyUtils.copy(e, new PsArticleModel());
            model.setPicture(OSSUtil.fillPath(model.getPicture()));
            return Stream.of(model);
        }).collect(Collectors.toList()));
    }



    @Override
    public RE getArticleDetail(Integer articleId) {
        PsArticle pojo = psArticleService.findByArticleId(articleId);
        if(OU.isBlack(pojo)){
            return RE.noData();
        }
        PsArticleModel model = CopyUtils.copy(pojo, new PsArticleModel());
        model.setPicture(OSSUtil.fillPath(model.getPicture()));
        addClickCount(articleId);
        return RE.ok(model);
    }

    private void addClickCount(Integer articleId) {
        // todo。此处应该使用redis的 分布式锁 机制
        psArticleService.addClickCount(articleId);
    }

}
