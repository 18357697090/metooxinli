package com.metoo.web.controller.ps;


import com.loongya.core.util.RE;
import com.metoo.api.ps.PsWatchArticleApi;
import org.apache.dubbo.config.annotation.DubboReference;
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
@RequestMapping("/ps/psWatchArticle")
public class PsWatchArticleController {

    @DubboReference
    private PsWatchArticleApi psWatchArticleApi;

    @GetMapping("/watchArticle")
    public RE watchArticle(@RequestHeader("UID")Integer uid, Integer articleId){
        return psWatchArticleApi.watchArticle(uid, articleId);

    }

}
