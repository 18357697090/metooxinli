package com.metoo.user.tj.service;

import com.metoo.pojo.login.model.LoginModel;
import com.metoo.pojo.login.vo.LoginVo;
import com.metoo.pojo.tj.model.TjUserInfoModel;
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

    LoginModel login(LoginVo vo);
}
