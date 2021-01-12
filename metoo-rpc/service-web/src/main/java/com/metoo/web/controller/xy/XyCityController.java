package com.metoo.web.controller.xy;


import com.loongya.core.util.RE;
import com.metoo.api.xy.XyCityApi;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 城表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/xy/xyCity")
public class XyCityController {

    @DubboReference
    private XyCityApi xyCityApi;

    /**
     * 点击城市按钮,查看是否有权限加入
     * 判断是否有权限加入的条件:
     * 1: 用户等级为3级,可以加入任何一个城市,除了主城
     * 2: 用户等级4级,不判断,任意加入
     * 3: 用户等级为2级, 是否有城市通行证,或者有城市增加资格
     *
     */


    /**
     *
     * 创建城市
     * @param uid
     * @param countryId
     * @return
     */
    //@ApiOperation("创建城")
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

    /**
     * 根据国家或部落获取城市列表
     * @param uid
     * @param countryId
     * @return
     */
    @ApiOperation("获取城列表")
    //获取城
    @PostMapping("/getCity")
    public RE getCity(@RequestHeader("UID")Integer uid, Integer countryId){
        return xyCityApi.getCity(uid, countryId);


    }

}
