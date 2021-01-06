package com.metoo.web.controller.ps;


import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsArticleUserApi;
import com.metoo.pojo.login.enums.AuthEnum;
import com.metoo.web.config.auth.ThreadLocal;
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
@RequestMapping("/ps/psArticleUser")
public class PsArticleUserController {

    @DubboReference
    private PsArticleUserApi psArticleUserApi;

    /**
     * 阅读文章的用户记录
     * @param articleId
     * @return
     */
    @GetMapping("/watchArticle")
    public RE watchArticle(Integer articleId){
        Integer userId = ThreadLocal.getUserId();
        if(OU.isBlack(userId)){
            return RE.fail(AuthEnum.LOGIN_TIMEOUT);
        }
        return psArticleUserApi.watchArticle(userId, articleId);

    }

}
