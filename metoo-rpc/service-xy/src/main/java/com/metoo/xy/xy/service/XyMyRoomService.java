package com.metoo.xy.xy.service;

import com.metoo.xy.xy.dao.entity.XyMyRoom;
import com.baomidou.mybatisplus.extension.service.IService;
import org.hibernate.loader.plan.spi.QuerySpace;

import java.util.List;

/**
 * <p>
 * 我的加入的聊天室表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface XyMyRoomService extends IService<XyMyRoom> {

    XyMyRoom findByMyRoomIdAndIsHost(Integer cityId);

    List<XyMyRoom> findByUidAndState(Integer uid);

    List<XyMyRoom> findBMyCityList(Integer uid);

    List<XyMyRoom> findByMyRoomId(Integer audioRoomId);
}
