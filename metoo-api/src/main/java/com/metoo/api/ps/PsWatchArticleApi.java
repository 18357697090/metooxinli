package com.metoo.api.ps;

import com.loongya.core.util.RE;

/**
 * <p>
 * 用户查看心理文章记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsWatchArticleApi {

    RE watchArticle(Integer uid, Integer articleId);
}
