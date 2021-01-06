package com.metoo.api.ps;

import com.loongya.core.util.RE;
import com.metoo.pojo.ps.vo.PsArticleVo;

/**
 * <p>
 * 心理文章表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsArticleApi {

    RE getArticleBoutiqueList(Integer count);

    RE getArticleBoutiqueMoreList(PsArticleVo vo);

    RE getArticleRecommendMoreList(PsArticleVo vo);

    RE getArticleDetail(Integer articleId);


}
