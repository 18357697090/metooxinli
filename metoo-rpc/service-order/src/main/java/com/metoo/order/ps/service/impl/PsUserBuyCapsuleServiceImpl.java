package com.metoo.order.ps.service.impl;

import com.metoo.api.order.PsUserBuyCapsuleApi;
import com.metoo.order.ps.dao.entity.PsUserBuyCapsule;
import com.metoo.order.ps.dao.mapper.PsUserBuyCapsuleMapper;
import com.metoo.order.ps.dao.repository.PsUserBuyCapsuleRepository;
import com.metoo.order.ps.service.PsUserBuyCapsuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metoo.pojo.order.model.PsUserBuyCapsuleModel;
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
public class PsUserBuyCapsuleServiceImpl extends ServiceImpl<PsUserBuyCapsuleMapper, PsUserBuyCapsule> implements PsUserBuyCapsuleService {

    @Autowired
    private PsUserBuyCapsuleRepository psUserBuyCapsuleRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public PsUserBuyCapsuleModel findByUidAndCapsuleId(Integer uid, Integer capsuleId) {
        PsUserBuyCapsule byUidAndCapsuleId = psUserBuyCapsuleRepository.findByUidAndCapsuleId(uid, capsuleId);
        return mapper.map(byUidAndCapsuleId, PsUserBuyCapsuleModel.class);
    }
}
