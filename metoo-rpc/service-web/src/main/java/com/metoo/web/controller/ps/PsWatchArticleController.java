package com.metoo.web.controller.ps;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户查看心理文章记录表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/ps-watch-article")
public class PsWatchArticleController {

    @GetMapping("/watchArticle")
    public void watchArticle(@RequestHeader("UID")Integer uid, Integer articleId){
        WatchArticle watchArticle = watchArticleDao.findByUid(uid);
        if (watchArticle==null){
            WatchArticle watchArticle1 = new WatchArticle();
            watchArticle1.setArticleId(articleId);
            watchArticle1.setUid(uid);
            watchArticleDao.save(watchArticle1);
            articleDao.updateNumber(articleId);
        }
    }

}
