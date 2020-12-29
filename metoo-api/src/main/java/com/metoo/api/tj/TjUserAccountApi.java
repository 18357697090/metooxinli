package com.metoo.api.tj;

import com.metoo.pojo.tj.model.TjUserAccountModel;

import java.math.BigDecimal;

/**
 * <p>
 * 用户账户表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface TjUserAccountApi {

    TjUserAccountModel findByUid(Integer uid);

    void updateBalance(BigDecimal subtract, Integer uid);
}
