package com.metoo.web.controller.xy;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 国度表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/xy/xy-country")
public class XyCountryController {

    @ApiOperation("创建国家")
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

    @ApiOperation("获取国家列表")
    @GetMapping("/getCountry")
    public List<ReturnCountryDTO> getCountry(@RequestHeader("UID")Integer uid, Integer raceId){
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

}
