package com.metoo.web.controller.ps;


import com.loongya.core.util.AssertUtils;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsArticleApi;
import com.metoo.api.ps.PsArticleBannerApi;
import com.metoo.pojo.ps.vo.PsArticleVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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


    private PsArticleBannerApi psArticleBannerApi;




    /**
     * 精品文章接口-随机获取四条数据
     * @return
     */
    @PostMapping("/getArticleBoutiqueList")
    public RE getArticleBoutiqueList(Integer count){
        if(OU.isBlack(count)){
            count = 4;
        }
        return  psArticleApi.getArticleBoutiqueList(count);
    }

    /**
     * 精品文章更多接口-分页查询
     * @return
     */
    @PostMapping("/getArticleBoutiqueMoreList")
    public RE getArticleBoutiqueMoreList(PsArticleVo vo){
        RE re = AssertUtils.checkParam(vo.getPagenum(), vo.getPagesize());
        if(re.isFail()){
            return re;
        }
        return psArticleApi.getArticleBoutiqueMoreList(vo);
    }

    /**
     * 更多推荐-分页查询
     * @return
     */
    @PostMapping("/getArticleRecommendMoreList")
    public RE getArticleRecommendMoreList(PsArticleVo vo){
        RE re = AssertUtils.checkParam(vo.getPagenum(), vo.getPagesize());
        if(re.isFail()){
            return re;
        }
        return  psArticleApi.getArticleRecommendMoreList(vo);
    }

    /**
     * 文章详情查询
     * @return
     */
    @GetMapping("/getArticleDetail")
    public RE getArticleDetail(Integer articleId){
        return psArticleApi.getArticleDetail(articleId);
    }

}
