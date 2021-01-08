package com.metoo.api.nr;

import com.loongya.core.util.RE;
import com.metoo.pojo.nr.vo.NrGoodsVo;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface NrGoodsApi{

    RE getGoodsList(NrGoodsVo vo);
}
