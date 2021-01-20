package com.metoo.ps.ps.service.impl;

import com.metoo.ps.ps.dao.entity.PsConsultOrder;
import com.metoo.ps.ps.dao.mapper.PsConsultOrderMapper;
import com.metoo.ps.ps.dao.repository.PsConsultOrderRepository;
import com.metoo.ps.ps.service.PsConsultOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author loongya
 * @since 2021-01-07
 */
@Service
public class PsConsultOrderServiceImpl extends ServiceImpl<PsConsultOrderMapper, PsConsultOrder> implements PsConsultOrderService {

    @Autowired
    private PsConsultOrderRepository psConsultOrderRepository;

    @Override
    public PsConsultOrder UnfinishedConsult(Integer userId,Integer ConId) {
         return psConsultOrderRepository.UnfinishedConsult(userId,ConId);
    }
}
