package com.metoo.order.nr.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.nr.NrGoodsApi;
import com.metoo.order.nr.dao.entity.NrGoods;
import com.metoo.order.nr.service.NrGoodsService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
public class NrGoodsApiImpl implements NrGoodsApi {

    @Resource
    private NrGoodsService nrGoodsService;

    @Override
    public RE findAll() {
        List<NrGoods> list = nrGoodsService.list();
        if(OU.isBlack(list)){
            return RE.noData();
        }
        return RE.ok(list);
    }
}
