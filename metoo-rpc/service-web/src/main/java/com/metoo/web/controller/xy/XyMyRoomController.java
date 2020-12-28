package com.metoo.web.controller.xy;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 我的加入的聊天室表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/xy/xy-my-room")
public class XyMyRoomController {


    @ApiOperation("我加入的国家或城的聊天室房间")
    //我的聊天室
    @GetMapping("/myRoom")
    public List<MyRoomDTO> myRoom(@RequestHeader("UID")Integer uid){
        List<MyRoom> myRooms = myRoomDao.findByUidAndState(uid);
        List<MyRoomDTO> myRoomDTOS = new ArrayList<>();
        for (MyRoom myRoom : myRooms){
            MyRoomDTO myRoomDTO = new MyRoomDTO();
            myRoomDTO.setRoomId(myRoom.getMyRoomId());
            switch (myRoom.getType()){
                case 1 :
                    Race race = raceDao.findByRaceId(myRoom.getMyRoomId());
                    myRoomDTO.setName(race.getName());
                    myRoomDTO.setPicture(race.getPicture());
                    myRoomDTO.setIntroduction(race.getIntroduction());
                    break;
                case 2 :
                    Country country = countryDao.findByCountryId(myRoom.getMyRoomId());
                    myRoomDTO.setName(country.getName());
                    myRoomDTO.setPicture(country.getPicture());
                    myRoomDTO.setIntroduction(country.getIntroduction());
                    break;
                case 3 :
                    City city =  cityDao.findByCityId(myRoom.getMyRoomId());
                    myRoomDTO.setName(city.getName());
                    myRoomDTO.setPicture(city.getPicture());
                    myRoomDTO.setIntroduction(city.getIntroduction());
                    break;
            }
            myRoomDTOS.add(myRoomDTO);
        }
        return myRoomDTOS;
    }

    //创建国度所需要的国家
    @GetMapping("/getMyCountryList")
    public List<ReturnMyCityList> getMyCountryList(@RequestHeader("UID")Integer uid){
        List<MyRoom> myCities = myRoomDao.findBMyCityList(uid);
        List<ReturnMyCityList> returnMyCityLists =  new ArrayList<>();
        for (MyRoom myRoom : myCities){
            ReturnMyCityList returnMyCityList = new ReturnMyCityList();
            returnMyCityList.setMyCityId(myRoom.getMyRoomId());
            returnMyCityList.setName(countryDao.findByCountryId(myRoom.getMyRoomId()).getName());
            returnMyCityLists.add(returnMyCityList);
        }
        return returnMyCityLists;
    }


    //进入聊天室检验
//    @GetMapping("/enterAudioRoom")
//    public ReturnMessage enterAudioRoom(@RequestHeader("UID")Integer uid,Integer audioRoomId){
//        ReturnMessage returnMessage = new ReturnMessage();
//        returnMessage.setState("error");
//        MyRoom myRoom = myRoomDao.findByMyRoomIdAndIsHost(audioRoomId);
//        Integer type = myRoom.getType();
//        MyRoom myRoom1=  myRoomDao.findByUidAndMyRoomId(uid,audioRoomId);
//        UserInfo userInfo = userInfoDao.findByUid(uid);
//        List<MyRoom> myRooms = myRoomDao.findByUid(uid);
//        if (myRoom1==null){
//            if (myRooms==null){
//                returnMessage.setState("success");
//                returnMessage.setExplain("你确定要加入该国家的聊天室吗？");
//            }else {
//                if(type==1){
//                    if(userInfo.getDw()<4){
//                        returnMessage.setExplain("你的段位不够，需要钻石及以上段位");
//                    }else {
//                        for (MyRoom room : myRooms) {
//                            if (room.getType()==2){
//                                if(countryDao.findByCountryId(room.getMyRoomId()).getRaceId().equals(audioRoomId)){
//                                    returnMessage.setExplain("由于跨国进入该聊天室，你需要消耗一张中级通行卡或者中级国籍卡");
//                                    return returnMessage;
//                                }
//                            }else {
//                                int x = cityDao.findByCityId(room.getMyRoomId()).getCountryId();
//                                if(countryDao.findByCountryId(x).getRaceId().equals(audioRoomId)){
//                                    returnMessage.setExplain("由于跨国进入该聊天室，你需要消耗一张中级通行卡或者中级国籍卡");
//                                    return returnMessage;
//                                }
//                            }
//                        }
//                        returnMessage.setExplain("由于跨族进入该聊天室，你需要消耗一张高级通行卡或者高级国籍卡");
//                        return returnMessage;
//                    }
//                }
//
//                if(type==2){
//                    if (userInfo.getDw()<3){
//                        returnMessage.setExplain("你的段位不够，需要黄金及以上段位");
//                    }else {
//                        for (MyRoom room : myRooms) {
//                            if (room.getType()==2){
//
//                            }else {
//
//                            }
//                        }
//                    }
//                }
//
//                if(type==3){
//                    if (userInfo.getDw()<2){
//                        returnMessage.setExplain("你的段位不够，需要白银及以上段位");
//                    }else {
//                        for (MyRoom room : myRooms) {
//                            if (room.getType()==3){
//                                if(room.getMyRoomId().equals(audioRoomId)){
//                                    returnMessage.setState("success");
//                                    returnMessage.setExplain("可以进入聊天室");
//                                    return returnMessage;
//                                }
//                            }else {
//                                returnMessage.setExplain("由于跨城进入该聊天室，你需要消耗一张低级通行卡或者低级国籍卡");
//                                return returnMessage;
//                            }
//                        }
//                    }
//                }
//
//            }
//        }else {
//            returnMessage.setState("success");
//        }
//
//        return returnMessage;
//    }


    //加入聊天室返回的数据
    @GetMapping("/joinAudioRoom")
    public JoinAudioRoomDTO joinAudioRoom(@RequestHeader("UID")Integer uid,Integer audioRoomId){
        JoinAudioRoomDTO joinAudioRoomDTO = new JoinAudioRoomDTO();
        List<MyRoom> myRooms = myRoomDao.findByMyRoomId(audioRoomId);
        List<UserInfo> userInfos = new ArrayList<>();
        for (int i = 0;i<3;i++){
            UserInfo userInfo = userInfoDao.findByUid(myRooms.get(i).getUid());
            userInfos.add(userInfo);
        }
        MyRoom myRoom = myRoomDao.findByMyRoomIdAndIsHost(audioRoomId);
        joinAudioRoomDTO.setHostUserInfo(userInfoDao.findByUid(myRoom.getUid()));
        joinAudioRoomDTO.setUserInfos(userInfos);
        joinAudioRoomDTO.setContent(myRoom.getNotice());
        return joinAudioRoomDTO;
    }

    //聊天室详情
//    @GetMapping("/detailAudioRoom")
//    public DetailAudioRoomDTO detailAudioRoom(@RequestHeader("UID")Integer uid,Integer roomId){
//        DetailAudioRoomDTO detailAudioRoomDTO = new DetailAudioRoomDTO();
//        List<MyRoom> myRooms =  myRoomDao.findByMyRoomId(roomId);
//        MyRoom myRoom = myRoomDao.findByMyRoomIdAndIsHost(roomId);
//        List<UserInfo> userInfos = new ArrayList<>();
//        return detailAudioRoomDTO;
//    }

}
