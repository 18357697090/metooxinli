package com.metoo.api.tj;

import com.metoo.pojo.tj.model.TjUserAccountDetailModel;

/**
 * <p>
 * 用户消费记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface TjUserAccountDetailApi {

    void save(TjUserAccountDetailModel zhRecord);
}
