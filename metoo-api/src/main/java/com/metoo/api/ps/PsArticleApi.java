package com.metoo.api.ps;

import com.loongya.core.util.RE;

/**
 * <p>
 * 心理文章表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsArticleApi {

    RE more(Integer page);

    RE content(Integer articleId);
}
