package com.metoo.web.controller.ps;


import com.loongya.core.util.RE;
import com.metoo.api.ps.PsArticleApi;
import org.apache.dubbo.config.annotation.DubboReference;
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
@RequestMapping("/ps/psArticle")
public class PsArticleController {

    @DubboReference
    private PsArticleApi psArticleApi;

    @GetMapping("/more")
    public RE more(Integer page){
        return psArticleApi.more(page);

    }

    @GetMapping("/content")
    public RE content(Integer articleId){
        return psArticleApi.content(articleId);


    }


}
