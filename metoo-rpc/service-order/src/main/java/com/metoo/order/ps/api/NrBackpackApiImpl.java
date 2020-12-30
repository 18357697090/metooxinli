package com.metoo.order.ps.api;

import com.loongya.core.util.RE;
import com.metoo.api.order.NrBackpackApi;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户背包商品表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
public class NrBackpackApiImpl implements NrBackpackApi {

    @Override
    public RE backpack(Integer uid) {
        return null;
    }

    @Override
    public RE buy(Integer uid, Integer type) {
        return null;
    }

    @Override
    public RE give(Integer uid, Integer type, Integer donee) {
        return null;
    }
}
