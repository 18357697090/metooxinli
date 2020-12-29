package com.metoo.user.tj.service;

import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.user.tj.dao.entity.TjUserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户个人信息表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface TjUserInfoService extends IService<TjUserInfo> {

    TjUserInfoModel findByUid(Integer uid);
}
