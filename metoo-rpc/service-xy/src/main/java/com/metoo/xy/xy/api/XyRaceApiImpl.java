package com.metoo.xy.xy.api;

import com.loongya.core.util.RE;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.api.xy.XyRaceApi;
import com.metoo.pojo.old.vo.ReturnRaceDTO;
import com.metoo.pojo.xy.model.XyRaceModel;
import com.metoo.xy.xy.dao.entity.XyRace;
import com.metoo.xy.xy.service.XyMyRoomService;
import com.metoo.xy.xy.service.XyRaceService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 国度，族表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class XyRaceApiImpl implements XyRaceApi {

    @Autowired
    private XyRaceService xyRaceService;

    @DubboReference
    private TjUserInfoApi tjUserInfoApi;

    @Autowired
    private XyMyRoomService xyMyRoomService;

    @Autowired
    private DozerBeanMapper mapper;


    @Override
    public RE getRace(Integer uid) {

        List<XyRace> races = xyRaceService.list();
        List<ReturnRaceDTO> returnRaceDTOS = new ArrayList<>();
        int dw = tjUserInfoApi.findByUid(uid).getLevel();
        for (XyRace race : races){
            ReturnRaceDTO returnRaceDTO = new ReturnRaceDTO();

            returnRaceDTO.setRace(mapper.map(race, XyRaceModel.class));
            if (dw>1){
                returnRaceDTO.setState(1);
            }
            returnRaceDTO.setUserName(tjUserInfoApi.findByUid(xyMyRoomService.findByMyRoomIdAndIsHost(race.getRaceId()).getUid()).getNickName());
            returnRaceDTOS.add(returnRaceDTO);
        }
        if(dw<4){
            returnRaceDTOS.get(0).setState(0);
            returnRaceDTOS.get(0).setStatusBar(2);
        }
        returnRaceDTOS.get(13).setState(1);

        return RE.ok(returnRaceDTOS);
    }
}
