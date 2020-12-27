package com.metoo.user.tj.service;

import com.metoo.user.tj.dao.entity.TjUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.loongya.core.util.RE;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-15
 */
public interface TjUserService extends IService<TjUser> {

    RE getList();

    TjUser getTjUserById(Integer id);
}
