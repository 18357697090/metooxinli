package com.metoo.web.controller.tj;


import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户个人信息表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/tj/tj-user-info")
public class TjUserInfoController {

    @GetMapping("/userInfo")
    public MeUserInfoDTO userInfo(@RequestHeader("UID")Integer uid1, Integer uid){
        MeUserInfoDTO meUserInfoDTO = mapper.map(userInfoDao.findByUid(uid),MeUserInfoDTO.class);
        Friend friend = friendDao.findByUidAndFriendId(uid1,uid);
        if (friend!=null){
            meUserInfoDTO.setState(friend.getState());
        }
        return meUserInfoDTO;
    }

    //修改个人信息
    @PostMapping("/modifyUserInfo")
    public ReturnMessage modifyUserInfo(@RequestBody ModifyUserIfoDTO modifyUserIfoDTO,@RequestHeader("UID")Integer uid){
        ReturnMessage returnMessage = new ReturnMessage();
        int i = userInfoDao.updateUserInfo(modifyUserIfoDTO.getName(),modifyUserIfoDTO.getPicture(),modifyUserIfoDTO.getCity(),modifyUserIfoDTO.getMotto(),uid);
        if(i==1){
            returnMessage.setState("success");
            returnMessage.setExplain("修改成功");
        }else {
            returnMessage.setState("error");
            returnMessage.setExplain("修改失败");
        }
        return returnMessage;
    }

    //上传个人信息
    @PostMapping("/userInfo")
    public String userInfo(@RequestBody UserInfoPojo userInfo,@RequestHeader("UID") Integer uid){
        System.out.println(uid);
        System.out.println(userInfo);
        UserInfo a= userInfoDao.findByUid(uid);
        UserInfo b= new UserInfo();
        if(a==null){
            b.setUid(uid);
            b.setAge(userInfo.getAge());
            b.setCity(userInfo.getCity());
            b.setDw(1);
            b.setGender(userInfo.getGender());
            b.setName(userInfo.getName());
            b.setPicture(userInfo.getPicture());
            userInfoDao.save(b);
            return "success";
        }
        else {
            return "error";
        }
    }


    //查找个人信息
    @GetMapping("/findUserInfo")
    public UserInfo findUserInfo(int uid){
        return userInfoDao.findByUid(uid);
    }

    //获取个人段位
    @GetMapping("/finddw")
    public Integer finddw(Integer uid){
        UserInfo a = userInfoDao.findByUid(uid);
        if(a==null){
            return -1;
        }else {
            return a.getDw();
        }
    }



}
