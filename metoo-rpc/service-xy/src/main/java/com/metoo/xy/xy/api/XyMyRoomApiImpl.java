package com.metoo.xy.xy.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.api.xy.XyMyRoomApi;
import com.metoo.pojo.old.vo.JoinAudioRoomDTO;
import com.metoo.pojo.old.vo.MyRoomDTO;
import com.metoo.pojo.old.vo.ReturnMyCityList;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.xy.xy.dao.entity.XyCity;
import com.metoo.xy.xy.dao.entity.XyCountry;
import com.metoo.xy.xy.dao.entity.XyMyRoom;
import com.metoo.xy.xy.dao.entity.XyRace;
import com.metoo.xy.xy.service.XyCityService;
import com.metoo.xy.xy.service.XyCountryService;
import com.metoo.xy.xy.service.XyMyRoomService;
import com.metoo.xy.xy.service.XyRaceService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 我的加入的聊天室表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class XyMyRoomApiImpl implements XyMyRoomApi {

    @Autowired
    private XyMyRoomService xyMyRoomService;

    @Autowired
    private XyRaceService xyRaceService;

    @Autowired
    private XyCountryService xyCountryService;

    @Autowired
    private XyCityService xyCityService;
    @Autowired
    private TjUserInfoApi tjUserInfoApi;

    @Override
    public RE myRoom(Integer uid) {

        List<XyMyRoom> myRooms = xyMyRoomService.findByUidAndState(uid);
        List<MyRoomDTO> myRoomDTOS = new ArrayList<>();
        for (XyMyRoom myRoom : myRooms){
            MyRoomDTO myRoomDTO = new MyRoomDTO();
            myRoomDTO.setRoomId(myRoom.getMyRoomId());
            switch (myRoom.getType()){
                case 1 :
                    XyRace race = xyRaceService.findByRaceId(myRoom.getMyRoomId());
                    myRoomDTO.setName(race.getName());
                    myRoomDTO.setPicture(race.getPicture());
                    myRoomDTO.setIntroduction(race.getIntroduction());
                    break;
                case 2 :
                    XyCountry country = xyCountryService.findByCountryId(myRoom.getMyRoomId());
                    myRoomDTO.setName(country.getName());
                    myRoomDTO.setPicture(country.getPicture());
                    myRoomDTO.setIntroduction(country.getIntroduction());
                    break;
                case 3 :
                    XyCity city =  xyCityService.findByCityId(myRoom.getMyRoomId());
                    myRoomDTO.setName(city.getName());
                    myRoomDTO.setPicture(city.getPicture());
                    myRoomDTO.setIntroduction(city.getIntroduction());
                    break;
            }
            myRoomDTOS.add(myRoomDTO);
        }
        if(OU.isBlack(myRoomDTOS)){
            return RE.noData();
        }
        return RE.ok(myRoomDTOS);
    }

    @Override
    public RE getMyCountryList(Integer uid) {

        List<XyMyRoom> myCities = xyMyRoomService.findBMyCityList(uid);
        List<ReturnMyCityList> returnMyCityLists =  new ArrayList<>();
        for (XyMyRoom myRoom : myCities){
            ReturnMyCityList returnMyCityList = new ReturnMyCityList();
            returnMyCityList.setMyCityId(myRoom.getMyRoomId());
            returnMyCityList.setName(xyCountryService.findByCountryId(myRoom.getMyRoomId()).getName());
            returnMyCityLists.add(returnMyCityList);
        }
        if(OU.isBlack(returnMyCityLists)){
            return RE.noData();
        }
        return RE.ok(returnMyCityLists);
    }

    @Override
    public RE joinAudioRoom(Integer uid, Integer audioRoomId) {

        JoinAudioRoomDTO joinAudioRoomDTO = new JoinAudioRoomDTO();
        List<XyMyRoom> myRooms = xyMyRoomService.findByMyRoomId(audioRoomId);
        List<TjUserInfoModel> userInfos = new ArrayList();
        for (int i = 0;i<3;i++){
            TjUserInfoModel userInfo = tjUserInfoApi.findByUid(myRooms.get(i).getUid());
            userInfos.add(userInfo);
        }
        XyMyRoom myRoom = xyMyRoomService.findByMyRoomIdAndIsHost(audioRoomId);
        joinAudioRoomDTO.setHostUserInfo(tjUserInfoApi.findByUid(myRoom.getUid()));
        joinAudioRoomDTO.setUserInfos(userInfos);
        joinAudioRoomDTO.setContent(myRoom.getNotice());
        return RE.ok(joinAudioRoomDTO);
    }
}
