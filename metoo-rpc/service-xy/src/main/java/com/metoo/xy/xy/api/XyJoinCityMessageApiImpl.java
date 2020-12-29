package com.metoo.xy.xy.api;

import com.loongya.core.util.RE;
import com.metoo.api.xy.XyJoinCityMessageApi;
import com.metoo.pojo.xy.vo.XyJoinCityMessageVo;
import com.metoo.xy.xy.dao.entity.XyJoinCityMessage;
import com.metoo.xy.xy.service.XyCityService;
import com.metoo.xy.xy.service.XyJoinCityMessageService;
import com.metoo.xy.xy.service.XyMyRoomService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 申请加入城消息记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public class XyJoinCityMessageApiImpl implements XyJoinCityMessageApi {

    @Autowired
    private XyJoinCityMessageService xyJoinCityMessageService;

    @Autowired
    private XyCityService xyCityService;

    @Autowired
    private XyMyRoomService xyMyRoomService;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public RE joinCity(Integer uid, XyJoinCityMessageVo vo) {
        if (vo.getCityHostId()==null||vo.getMessage()==null){
            return RE.serviceFail("填写信息不完全");
        }

        XyJoinCityMessage cityMessage = mapper.map(vo, XyJoinCityMessage.class);
        cityMessage.setUid(uid);
        cityMessage.setCityName(xyCityService.findByCityId(cityMessage.getCityHostId()).getName());
        cityMessage.setCityHostId(xyMyRoomService.findByMyRoomIdAndIsHost(cityMessage.getCityHostId()).getUid());
        xyJoinCityMessageService.save(cityMessage);
        return RE.ok("申请加入成功");
    }
}
