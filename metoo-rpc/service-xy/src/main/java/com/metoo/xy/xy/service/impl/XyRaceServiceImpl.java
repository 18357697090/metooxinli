package com.metoo.xy.xy.service.impl;

import com.metoo.xy.xy.dao.entity.XyRace;
import com.metoo.xy.xy.dao.mapper.XyRaceMapper;
import com.metoo.xy.xy.dao.repository.XyRaceRepository;
import com.metoo.xy.xy.service.XyRaceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 国度，族表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class XyRaceServiceImpl extends ServiceImpl<XyRaceMapper, XyRace> implements XyRaceService {

    @Autowired
    private XyRaceRepository xyRaceRepository;

    @Override
    public XyRace findByRaceId(Integer raceId) {
        return xyRaceRepository.findByRaceId(raceId);
    }
}
