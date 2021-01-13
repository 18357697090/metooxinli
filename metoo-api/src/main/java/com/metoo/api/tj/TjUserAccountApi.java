package com.metoo.api.tj;

import com.loongya.core.util.RE;
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

    RE updateBalance(BigDecimal subtract, Integer uid);

    RE me(Integer uid);

    RE findzh(Integer uid);

    RE findBalance(Integer uid);

    RE updatePsCoin(BigDecimal prices, Integer uid);

    void updateBalanceUp(BigDecimal total, Integer uid);
}
