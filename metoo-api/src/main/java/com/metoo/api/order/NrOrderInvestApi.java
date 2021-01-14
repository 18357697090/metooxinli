package com.metoo.api.order;

import com.loongya.core.util.RE;
import com.metoo.pojo.nr.vo.NrGoodsVo;
import com.metoo.pojo.nr.vo.NrOrderInvestVo;
import com.metoo.pojo.order.vo.NrBackpackVo;

/**
 * <p>
 * 用户背包商品表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface NrOrderInvestApi {

    RE createInvestOrder(NrOrderInvestVo vo);
    RE investOrderSuccessBack(String out_trade_no);
}
