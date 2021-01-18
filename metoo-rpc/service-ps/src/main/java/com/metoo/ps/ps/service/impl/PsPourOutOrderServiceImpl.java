package com.metoo.ps.ps.service.impl;

import com.metoo.pojo.ps.vo.PsConsultVo;
import com.metoo.ps.ps.dao.entity.PsPourOutOrder;
import com.metoo.ps.ps.dao.mapper.PsPourOutOrderMapper;
import com.metoo.ps.ps.dao.repository.PsPourOutOrderRepository;
import com.metoo.ps.ps.service.PsPourOutOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 倾诉师订单 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2021-01-07
 */
@Service
public class PsPourOutOrderServiceImpl extends ServiceImpl<PsPourOutOrderMapper, PsPourOutOrder> implements PsPourOutOrderService {

    @Autowired
    PsPourOutOrderRepository psPourOutOrderRepository;

    @Override
    public PsPourOutOrder UnfinishedConsult(PsConsultVo vo) {
        return psPourOutOrderRepository.UnfinishedConsult(vo.getUserId(),vo.getConId());

    }
}
