package com.metoo.order.nr.service.impl;

import com.metoo.order.nr.dao.entity.NrBackpack;
import com.metoo.order.nr.dao.mapper.NrBackpackMapper;
import com.metoo.order.nr.dao.repository.NrBackpackRepository;
import com.metoo.order.nr.service.NrBackpackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户背包商品表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class NrBackpackServiceImpl extends ServiceImpl<NrBackpackMapper, NrBackpack> implements NrBackpackService {

    @Autowired
    private NrBackpackRepository nrBackpackRepository;

    @Override
    public List<NrBackpack> findByUid(Integer uid) {
        return nrBackpackRepository.findByUid(uid);
    }

    @Override
    public void updateGoodsNumber(Integer userId, Integer goodsId) {
        nrBackpackRepository.updateGoodsNumber(userId, goodsId);
    }

    @Override
    public void updateGoodsNumDownById(Integer id) {
        nrBackpackRepository.updateGoodsNumDownById(id);
    }

    @Override
    public NrBackpack findFirstByUidAndGoodsId(Integer userId, Integer goodsId) {
        return nrBackpackRepository.findFirstByUidAndGoodsIdOrderByCreateTimeDesc(userId, goodsId);
    }

}
