package com.metoo.order.nr.service;

import com.metoo.order.nr.dao.entity.NrBackpack;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户背包商品表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface NrBackpackService extends IService<NrBackpack> {

    List<NrBackpack> findByUid(Integer uid);

    NrBackpack findFirstByUidAndGoodsId(Integer userId, Integer goodsId);

    void updateGoodsNumber(Integer userId, Integer goodsId);

    void updateGoodsNumDownById(Integer id);
}
