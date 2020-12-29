package com.metoo.user.tj.api;

import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.user.tj.service.TjUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户个人信息表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public class TjUserInfoApiImpl implements TjUserInfoApi {

    @Autowired
    private TjUserInfoService tjUserInfoService;

    @Override
    public TjUserInfoModel findByUid(Integer uid) {
        return tjUserInfoService.findByUid(uid);
    }
}
