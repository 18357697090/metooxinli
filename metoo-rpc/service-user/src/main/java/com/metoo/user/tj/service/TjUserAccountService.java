package com.metoo.user.tj.service;

import com.metoo.user.tj.dao.entity.TjUserAccount;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 * 用户账户表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface TjUserAccountService extends IService<TjUserAccount> {

    void updateBalance(BigDecimal subtract, Integer uid);

    TjUserAccount findByUid(Integer uid);

    TjUserAccount findBalance(Integer id);

    void updatePsCoin(BigDecimal price, Integer uid);

    void frozeenBalance(Integer uid, BigDecimal price);

    void updateBalanceUp(BigDecimal price, Integer uid);

    void unFrozeenBalance(Integer uid, BigDecimal price);

}
