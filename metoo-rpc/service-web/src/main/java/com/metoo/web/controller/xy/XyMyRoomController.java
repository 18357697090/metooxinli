package com.metoo.web.controller.xy;


import com.loongya.core.util.RE;
import com.metoo.api.xy.XyMyRoomApi;
import com.metoo.pojo.old.vo.ReturnMyCityList;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
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
@RequestMapping("/xy/xyMyRoom")
public class XyMyRoomController {

    @DubboReference
    private XyMyRoomApi xyMyRoomApi;

    @ApiOperation("我加入的国家或城的聊天室房间")
    //我的聊天室
    @GetMapping("/myRoom")
    public RE myRoom(@RequestHeader("UID")Integer uid){
        return xyMyRoomApi.myRoom(uid);
    }

    //创建国度所需要的国家
    @GetMapping("/getMyCountryList")
    public RE getMyCountryList(@RequestHeader("UID")Integer uid){
        return xyMyRoomApi.getMyCountryList(uid);
    }

    //加入聊天室返回的数据
    @GetMapping("/joinAudioRoom")
    public RE joinAudioRoom(@RequestHeader("UID")Integer uid,Integer audioRoomId){
        return xyMyRoomApi.joinAudioRoom(uid, audioRoomId);

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
