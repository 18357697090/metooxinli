package com.metoo.order.ps.service.impl;

import com.metoo.api.order.PsCapsuleOrderApi;
import com.metoo.order.ps.dao.entity.PsCapsuleOrder;
import com.metoo.order.ps.dao.mapper.PsCapsuleOrderMapper;
import com.metoo.order.ps.dao.repository.PsCapsuleOrderRepository;
import com.metoo.order.ps.service.PsCapsuleOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metoo.pojo.order.model.PsCapsuleOrderModel;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户购买胶囊记录表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class PsCapsuleOrderServiceImpl extends ServiceImpl<PsCapsuleOrderMapper, PsCapsuleOrder> implements PsCapsuleOrderService {

    @Autowired
    private PsCapsuleOrderRepository psCapsuleOrderRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public PsCapsuleOrderModel findByUidAndCapsuleId(Integer uid, Integer capsuleId) {
        PsCapsuleOrder byUidAndCapsuleId = psCapsuleOrderRepository.findByUidAndCapsuleId(uid, capsuleId);
        return mapper.map(byUidAndCapsuleId, PsCapsuleOrderModel.class);
    }
}
