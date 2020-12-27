package com.metoo.user.tj.service;

import com.loongya.core.util.RE;
import com.metoo.user.tj.dao.entity.TjUserAccount;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface TjUserAccountService extends IService<TjUserAccount> {

    RE getUserAccountByUserId(Integer userId);
}
