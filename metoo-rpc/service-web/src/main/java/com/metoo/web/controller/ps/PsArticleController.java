package com.metoo.web.controller.ps;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 心理文章表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/ps-article")
public class PsArticleController {

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


}
