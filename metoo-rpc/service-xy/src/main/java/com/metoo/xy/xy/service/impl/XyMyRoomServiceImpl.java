package com.metoo.xy.xy.service.impl;

import com.metoo.xy.xy.dao.entity.XyMyRoom;
import com.metoo.xy.xy.dao.mapper.XyMyRoomMapper;
import com.metoo.xy.xy.dao.repository.XyMyRoomRepository;
import com.metoo.xy.xy.service.XyMyRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hibernate.loader.plan.spi.QuerySpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 我的加入的聊天室表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class XyMyRoomServiceImpl extends ServiceImpl<XyMyRoomMapper, XyMyRoom> implements XyMyRoomService {

    @Autowired
    private XyMyRoomRepository xyMyRoomRepository;

    @Override
    public XyMyRoom findByMyRoomIdAndIsHost(Integer cityId) {
        return xyMyRoomRepository.findByMyRoomIdAndIsHost(cityId);
    }

    @Override
    public List<XyMyRoom> findByUidAndState(Integer uid) {
        return xyMyRoomRepository.findByUidAndState(uid);
    }

    @Override
    public List<XyMyRoom> findBMyCityList(Integer uid) {
        return xyMyRoomRepository.findBMyCityList(uid);
    }

    @Override
    public List<XyMyRoom> findByMyRoomId(Integer audioRoomId) {
        return xyMyRoomRepository.findByMyRoomId(audioRoomId);
    }
}
