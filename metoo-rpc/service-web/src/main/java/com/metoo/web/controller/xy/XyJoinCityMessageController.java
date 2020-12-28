package com.metoo.web.controller.xy;


import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 申请加入城消息记录表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/xy/xy-join-city-message")
public class XyJoinCityMessageController {

    //加入国度
    @GetMapping("/joinCity")
    public ReturnMessage joinCity(@RequestHeader("UID")Integer uid, @RequestBody JoinCityMessage cityMessage){
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


    //查看请求
//    @GetMapping()
//    public ReturnMessage (){
//    ReturnMessage returnMessage = new ReturnMessage();
//
//    return returnMessage;
//}

}
