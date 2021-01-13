package com.metoo.order.nr.service;

import com.metoo.order.nr.dao.entity.NrOrderInvest;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 充值订单表 服务类
 * </p>
 *
 * @author loongya
 * @since 2021-01-13
 */
public interface NrOrderInvestService extends IService<NrOrderInvest> {

    NrOrderInvest findFirstByOrderNo(String orderNo);

    void investOrderSuccessBack(String out_trade_no);
}
