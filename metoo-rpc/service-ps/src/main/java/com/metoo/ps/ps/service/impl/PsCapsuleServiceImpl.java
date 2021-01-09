package com.metoo.ps.ps.service.impl;

import com.metoo.pojo.ps.vo.PsCapsuleVo;
import com.metoo.ps.ps.dao.entity.PsCapsule;
import com.metoo.ps.ps.dao.mapper.PsCapsuleMapper;
import com.metoo.ps.ps.dao.repository.PsCapsuleRepository;
import com.metoo.ps.ps.service.PsCapsuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;

/**
 * <p>
 * 心理倾诉胶囊（备忘录功能）表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class PsCapsuleServiceImpl extends ServiceImpl<PsCapsuleMapper, PsCapsule> implements PsCapsuleService {

    @Autowired
    private PsCapsuleRepository psCapsuleRepository;

    @Override
    public PsCapsule findByCapsuleId(Integer capsuleId) {
        return psCapsuleRepository.getOne(capsuleId);
    }

    @Override
    public List<PsCapsule> findCapsule() {
        return psCapsuleRepository.findCapsule();
    }

    @Override
    public List<PsCapsule> findCapsuleRand(Integer limit) {
        return psCapsuleRepository.findAllByRand(limit);
    }

    @Override
    public void updateReadNum(Integer capsuleId) {
        psCapsuleRepository.updateReadNum(capsuleId);
    }
}
