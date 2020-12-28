package com.metoo.ps.ps.service.impl;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.ps.ps.dao.entity.PsScale;
import com.metoo.ps.ps.dao.mapper.PsScaleMapper;
import com.metoo.ps.ps.dao.repository.PsScaleRepository;
import com.metoo.ps.ps.dao.repository.ScaleDao;
import com.metoo.ps.ps.service.PsScaleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 心理测量量表表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class PsScaleServiceImpl extends ServiceImpl<PsScaleMapper, PsScale> implements PsScaleService {

    @Resource
    private PsScaleRepository psScaleRepository;

    @Override
    public RE cl(Integer page) {
        Pageable pageable= PageRequest.of(page,7);
        List<PsScale> allBySpare = psScaleRepository.findAllBySpare(1, pageable);
        if(OU.isBlack(allBySpare)){
            return RE.noData();
        }
        return RE.ok(allBySpare);
    }
}
