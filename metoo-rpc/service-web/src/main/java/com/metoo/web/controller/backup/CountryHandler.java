package com.metoo.web.controller.backup;

import com.metoo.metoo.CountryDTO.*;
import com.metoo.metoo.DTO.JoinAudioRoomDTO;
import com.metoo.metoo.DTO.MyRoomDTO;
import com.metoo.metoo.DTO.ReturnMyCityList;
import com.metoo.metoo.entity.*;
import com.metoo.metoo.repository.*;
import com.metoo.metoo.tools.ReturnMessage;
import com.metoo.metoo.tools.createID;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryHandler {
    @Autowired
    private CountryDao countryDao;
    @Autowired
    private Mapper mapper;
    @Autowired
    private ZhDao zhDao;
    @Autowired
    private MyRoomDao myRoomDao;
    @Autowired
    private ZhRecordDao zhRecordDao;
    @Autowired
    private RaceDao raceDao;
    @Autowired
    private CityDao cityDao;
    @Autowired
    private AudioRoomChatRecordDao audioRoomChatRecordDao;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private JoinCityMessageDao joinCityMessageDao;

    //创建国
    @PostMapping("/buildCountry")
    public ReturnMessage buildCountry(@RequestBody buildCountryDTO buildCountryDTO, @RequestHeader("UID")Integer uid){
        ReturnMessage returnMessage = new ReturnMessage();
        returnMessage.setState( "error");
        if(buildCountryDTO.getRaceId()!=null
            &&buildCountryDTO.getName()!=null&&!buildCountryDTO.getName().equals("")
            &&buildCountryDTO.getPicture()!=null&&!buildCountryDTO.getPicture().equals("")
            &&buildCountryDTO.getIntroduction()!=null&&!buildCountryDTO.getIntroduction().equals("")) {
            UserInfo userInfo = userInfoDao.findByUid(uid);
            if (userInfo.getDw()<4){
                returnMessage.setExplain("等级不够，需要钻石段位及以上才可以创建国度");
            }else {
                BigDecimal bigDecimal = new BigDecimal("5000");
                Zh zh = zhDao.findByUid(uid);
                BigDecimal balance = zh.getBalance().subtract(bigDecimal);
                if(balance.compareTo(BigDecimal.ZERO)<0){
                    returnMessage.setExplain("你的活跃积分不足");
                }else {
                    String name = buildCountryDTO.getName();
                    if (name.length() > 6) {
                        returnMessage.setExplain("填写名称太长");
                    } else {
                        Country country2 = countryDao.findByName(buildCountryDTO.getName());
                        if (country2 != null) {
                            returnMessage.setExplain("国度名字已存在");
                        } else {
                            zhDao.updateBalance(balance, uid);
                            Country country = mapper.map(buildCountryDTO, Country.class);
                            int countryId = createID.create();
                            Country country1 = countryDao.findByCountryId(countryId);
                            City city = cityDao.findByCityId(countryId);
                            while (country1 != null && city != null) {
                                countryId = createID.create();
                                city = cityDao.findByCityId(countryId);
                                country1 = countryDao.findByCountryId(countryId);
                            }
                            country.setCountryId(countryId);
                            country.setState(1);
                            countryDao.save(country);
                            MyRoom myRoom = new MyRoom();
                            myRoom.setIsHost(1);
                            myRoom.setUid(uid);
                            myRoom.setMyRoomId(countryId);
                            myRoom.setType(2);
                            myRoomDao.save(myRoom);
                            ZhRecord zhRecord = new ZhRecord();
                            zhRecord.setUid(uid);
                            zhRecord.setPrices(bigDecimal);
                            zhRecord.setType("创建国度");
                            zhRecord.setContent(buildCountryDTO.getName());
                            zhRecordDao.save(zhRecord);
                            returnMessage.setState("success");
                            returnMessage.setExplain("创建成功");
                        }
                    }
                }
            }
        }
        else {
            returnMessage.setExplain("填写信息不完全");
        }

        return returnMessage;
    }

    //创建城或者馆
//    @PostMapping("/buildCity")
//    public ReturnMessage buildCity(@RequestBody BuildCityDTO buildCityDTO, @RequestHeader("UID")Integer uid){
//        System.out.println("创建城" + buildCityDTO);
//        ReturnMessage returnMessage = new ReturnMessage();
//        System.out.println(buildCityDTO);
//        returnMessage.setState( "error");
//        if(buildCityDTO.getIntroduction()==null||buildCityDTO.getIntroduction().equals("")
//                || buildCityDTO.getPicture()==null|| buildCityDTO.getPicture().equals("")
//                || buildCityDTO.getName()==null|| buildCityDTO.getName().equals("")
//                || buildCityDTO.getType()==null
//                || buildCityDTO.getCountryId()==null){
//            returnMessage.setExplain("填写信息不完全");
//            return returnMessage;
//        }
//        UserInfo userInfo = userInfoDao.findByUid(uid);
//        if (userInfo.getDw()<3) {
//            returnMessage.setExplain("段位不够,需要黄金及以上才可以。");
//            return returnMessage;
//        }
//        String name = buildCityDTO.getName();
//        if (name.length()>6){
//            returnMessage.setExplain("名字太长");
//            return returnMessage;
//        }
//        BigDecimal bigDecimal = new BigDecimal("500");
//        Zh zh = zhDao.findByUid(uid);
//        BigDecimal balance = zh.getBalance().subtract(bigDecimal);
//        if(balance.compareTo(BigDecimal.ZERO)<0){
//            returnMessage.setExplain("你的活跃积分不足");
//            return returnMessage;
//        }else {
//            zhDao.updateBalance(balance,uid);
//            City city = mapper.map(buildCityDTO,City.class);
//            int cityId = createID.create();
//            Country country = countryDao.findByCountryId(cityId);
//            City city1 = cityDao.findByCityId(cityId);
//            while (country!=null&&city1!=null){
//                cityId = createID.create();
//                city1 = cityDao.findByCityId(cityId);
//                country = countryDao.findByCountryId(cityId);
//            }
//            city.setRaceId(countryDao.findByCountryId(city.getCountryId()).getRaceId());
//            city.setCityId(cityId);
//            city.setState(1);
//            cityDao.save(city);
//            MyRoom myRoom = new MyRoom();
//            myRoom.setIsHost(1);
//            myRoom.setUid(uid);
//            myRoom.setMyRoomId(cityId);
//            myRoom.setType(3);
//            myRoomDao.save(myRoom);
//            ZhRecord zhRecord = new ZhRecord();
//            zhRecord.setUid(uid);
//            zhRecord.setPrices(bigDecimal);
//            // 1 为正常的城，2，商家馆  3，倾诉馆 4，请安馆 5，匹配
//            switch (city.getType()){
//                case 1 :
//                    zhRecord.setType("创建城");
//                    break;
//                case 2 :
//                    zhRecord.setType("创建商家馆");
//                    break;
//                case 3 :
//                    zhRecord.setType("创建倾诉馆");
//                    break;
//                case 4 :
//                    zhRecord.setType("创建请安馆");
//                    break;
//                case 5 :
//                    zhRecord.setType("创建匹配馆");
//                    break;
//                default:
//                    zhRecord.setType("创建城");
//                    break;
//            }
//            zhRecordDao.save(zhRecord);
//            returnMessage.setState("success");
//            returnMessage.setExplain("成功");
//            return returnMessage;
//        }
//    }

    //获取族
    @GetMapping("/getRace")
    public List<ReturnRaceDTO> getRace(@RequestHeader("UID")Integer uid){
        List<Race> races = raceDao.findAll();
        List<ReturnRaceDTO> returnRaceDTOS = new ArrayList<>();
        int dw = userInfoDao.findByUid(uid).getDw();
        for (Race race : races){
            ReturnRaceDTO returnRaceDTO = new ReturnRaceDTO();
            returnRaceDTO.setRace(race);
            if (dw>1){
                returnRaceDTO.setState(1);
            }
            returnRaceDTO.setUserName(userInfoDao.findByUid(myRoomDao.findByMyRoomIdAndIsHost(race.getRaceId()).getUid()).getName());
            returnRaceDTOS.add(returnRaceDTO);
        }
        if(dw<4){
            returnRaceDTOS.get(0).setState(0);
            returnRaceDTOS.get(0).setStatusBar(2);
        }
        returnRaceDTOS.get(13).setState(1);
        return returnRaceDTOS;
    }

    //获取国
    @GetMapping("/getCountry")
    public List<ReturnCountryDTO> getCountry(@RequestHeader("UID")Integer uid,Integer raceId){
        Race race =  raceDao.findByRaceId(raceId);
        ReturnCountryDTO returnCountryDTO = mapper.map(race,ReturnCountryDTO.class);
        returnCountryDTO.setCountryId(raceId);
        returnCountryDTO.setStatusBar(2);
        returnCountryDTO.setUserName(userInfoDao.findByUid(myRoomDao.findByMyRoomIdAndIsHost(raceId).getUid()).getName());
        List<ReturnCountryDTO> returnCountryDTOS = new ArrayList<>();
        returnCountryDTOS.add(returnCountryDTO);
        List<Country> countries = countryDao.findByRaceId(raceId);
        for (Country country : countries) {
            ReturnCountryDTO returnCountryDTO1 = mapper.map(country,ReturnCountryDTO.class);
            returnCountryDTO1.setUserName(userInfoDao.findByUid(myRoomDao.findByMyRoomIdAndIsHost(country.getCountryId()).getUid()).getName());
            returnCountryDTOS.add(returnCountryDTO1);
        }
        return returnCountryDTOS;
    }

    //获取城
    @GetMapping("/getCity")
    public List<ReturnCityDTO> getCity(@RequestHeader("UID")Integer uid, Integer countryId){
        List<City> cities = cityDao.findByCountryId(countryId);
        Country country = countryDao.findByCountryId(countryId);
        ReturnCityDTO returnCityDTO1 = new ReturnCityDTO();
        returnCityDTO1.setCityId(countryId);
        List<ReturnCityDTO> returnCityDTOS = new ArrayList<>();
        for (City city : cities){
            ReturnCityDTO returnCityDTO = mapper.map(city,ReturnCityDTO.class);
            returnCityDTO.setUsername(userInfoDao.findByUid(myRoomDao.findByMyRoomIdAndIsHost(city.getCityId()).getUid()).getName());
            returnCityDTOS.add(returnCityDTO);
        }
        return returnCityDTOS;
    }

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

    //查看群的聊天记录
    @GetMapping("/audioRoomChatRecord")
    public List<String> audioRoomChatRecord(Integer audioRoomId,Integer page){
        Pageable pageable = PageRequest.of(page,20, Sort.Direction.DESC,"id");
        List<AudioRoomChatRecord> audioRoomChatRecords=  audioRoomChatRecordDao.findByAudioRoomId(audioRoomId,pageable);
        List<String> strings = new ArrayList<>();
        for (AudioRoomChatRecord audioRoomChatRecord : audioRoomChatRecords){
            String content = audioRoomChatRecord.getContent();
            strings.add(content);
        }
        return strings;
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

    //加入国度
    @GetMapping("/joinCity")
    public ReturnMessage joinCity(@RequestHeader("UID")Integer uid,@RequestBody JoinCityMessage cityMessage){
        ReturnMessage returnMessage = new ReturnMessage();
        if (cityMessage.getCityHostId()==null||cityMessage.getMessage()==null){
            returnMessage.setExplain("填写信息不完全");
        }else {
            cityMessage.setUid(uid);
            cityMessage.setCityName(cityDao.findByCityId(cityMessage.getCityHostId()).getName());
            cityMessage.setCityHostId(myRoomDao.findByMyRoomIdAndIsHost(cityMessage.getCityHostId()).getUid());
            joinCityMessageDao.save(cityMessage);
            returnMessage.setExplain("申请加入成功");
        }
        return returnMessage;
    }

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



    //查看请求
//    @GetMapping()
//    public ReturnMessage (){
//    ReturnMessage returnMessage = new ReturnMessage();
//
//    return returnMessage;
//}


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
