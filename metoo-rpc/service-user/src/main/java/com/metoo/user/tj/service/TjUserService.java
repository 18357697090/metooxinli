package com.metoo.user.tj.service;

import com.metoo.user.tj.dao.entity.TjUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.metoo.user.tj.dao.entity.TjUserInfo;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface TjUserService extends IService<TjUser> {

    TjUser findByUsername(String username);

    TjUser findByUid(Integer x);

    void updateUserPassword(String newPassword, String username);
}
