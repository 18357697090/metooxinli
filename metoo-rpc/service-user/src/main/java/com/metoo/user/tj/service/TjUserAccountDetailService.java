package com.metoo.user.tj.service;

import com.metoo.pojo.tj.model.TjUserAccountDetailAddDetailModel;
import com.metoo.user.tj.dao.entity.TjUserAccountDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户消费记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface TjUserAccountDetailService extends IService<TjUserAccountDetail> {

    void insertDetails(TjUserAccountDetailAddDetailModel acModel);
}
