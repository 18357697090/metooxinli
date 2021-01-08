package com.metoo.api.order;

import com.loongya.core.util.RE;
import com.metoo.pojo.nr.vo.NrGoodsVo;
import com.metoo.pojo.order.vo.NrBackpackVo;

/**
 * <p>
 * 用户背包商品表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface NrBackpackApi {

    RE myBackpackList(NrBackpackVo vo);

    RE buyGoods(NrGoodsVo vo);

    RE giveGoods(NrGoodsVo vo);

    RE buyAndGiveGoods(NrGoodsVo vo);
}
