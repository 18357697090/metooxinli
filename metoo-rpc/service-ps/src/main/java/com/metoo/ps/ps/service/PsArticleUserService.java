package com.metoo.ps.ps.service;

import com.metoo.ps.ps.dao.entity.PsArticleUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户查看心理文章记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsArticleUserService extends IService<PsArticleUser> {

    PsArticleUser findByUid(Integer uid);
}
