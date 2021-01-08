package com.metoo.order.nr.service.impl;

import com.metoo.order.nr.dao.entity.NrGoods;
import com.metoo.order.nr.dao.mapper.NrGoodsMapper;
import com.metoo.order.nr.dao.repository.NrGoodsRepository;
import com.metoo.order.nr.service.NrGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class NrGoodsServiceImpl extends ServiceImpl<NrGoodsMapper, NrGoods> implements NrGoodsService {

    @Autowired
    private NrGoodsRepository nrGoodsRepository;

}
