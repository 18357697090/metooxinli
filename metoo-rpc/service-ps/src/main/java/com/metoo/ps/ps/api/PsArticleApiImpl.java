package com.metoo.ps.ps.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsArticleApi;
import com.metoo.pojo.old.vo.ArticleDTO;
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

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public RE more(Integer page) {
        Pageable pageable = PageRequest.of(page,5, Sort.Direction.DESC,"sort");
        List<PsArticle> articles = psArticleService.findByState(1,pageable);
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        for (PsArticle article : articles){
            ArticleDTO articleDTO = mapper.map(article,ArticleDTO.class);
            articleDTOS.add(articleDTO);
        }
        if(OU.isBlack(articleDTOS)){
            return RE.noData();
        }
        return RE.ok(articleDTOS);
    }

    @Override
    public RE content(Integer articleId) {
        return RE.ok(psArticleService.findByArticleId(articleId).getContent());
    }
}
