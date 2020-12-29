package com.metoo.api.tj;

import com.metoo.pojo.tj.model.TjUserInfoModel;

/**
 * <p>
 * 用户个人信息表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface TjUserInfoApi {

    TjUserInfoModel findByUid(Integer uid);
}
