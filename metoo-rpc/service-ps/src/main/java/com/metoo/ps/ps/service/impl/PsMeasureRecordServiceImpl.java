package com.metoo.ps.ps.service.impl;

import com.metoo.ps.ps.dao.entity.PsMeasureRecord;
import com.metoo.ps.ps.dao.mapper.PsMeasureRecordMapper;
import com.metoo.ps.ps.dao.repository.PsMeasureRecordRepository;
import com.metoo.ps.ps.service.PsMeasureRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户心理测量量表记录表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class PsMeasureRecordServiceImpl extends ServiceImpl<PsMeasureRecordMapper, PsMeasureRecord> implements PsMeasureRecordService {

    @Autowired
    private PsMeasureRecordRepository psMeasureRecordRepository;

    @Override
    public List<PsMeasureRecord> findBytime(String time, Integer uid) {
        return psMeasureRecordRepository.findBytime(time, uid);
    }
}
