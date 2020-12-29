package com.metoo.api.order;

import com.loongya.core.util.RE;

/**
 * <p>
 * 用户背包商品表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface NrBackpackApi {

    RE backpack(Integer uid);

    RE buy(Integer uid, Integer type);

    RE give(Integer uid, Integer type, Integer donee);
}
