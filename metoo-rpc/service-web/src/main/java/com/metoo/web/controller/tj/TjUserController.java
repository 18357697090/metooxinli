package com.metoo.web.controller.tj;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/tj/tj-user")
public class TjUserController {

    //搜索人，添加好友
    @GetMapping("/findFriend")
    public List<FriendListDto> fendFriend(@RequestHeader("UID")Integer uid, String name){
        List<UserInfo> userInfos = userInfoDao.findByNameLike("%"+name+"%");
        List<FriendListDto> friendListDtos = new ArrayList<>();
        for (UserInfo userInfo : userInfos){
            if (!uid.equals(userInfo.getUid())){
                FriendListDto friendListDto = mapper.map(userInfo,FriendListDto.class);
                friendListDtos.add(friendListDto);
            }
        }
        return friendListDtos;
    }


    //注册功能
    @PostMapping("/register")
    public zc register(@RequestBody LoginPojo loginPojo){
        System.out.println(loginPojo);
        zc zc=new zc();
        if(loginPojo.getAnswer().equals("")&&loginPojo.getPassword().equals("")&&loginPojo.getSecret().equals("")&&loginPojo.getUsername().equals("")){
            zc.setState("error");
            return zc;
        }
        String username=loginPojo.getUsername();
        User a = userDao.findByUsername(username);
        if(a!=null){
            zc.setState("exist");
            return zc;
        }
        String password=loginPojo.getPassword();
        int x=createID.create();
        User b=userDao.findByUid(x);
        while (b!=null){
            x= createID.create();
            b=userDao.findByUid(x);
        }
        zc.setUid(x);
        User user=new User();
        user.setPassword(password);
        user.setUid(x);
        user.setUsername(username);
        userDao.save(user);
        Zh zh=new Zh();
        zh.setUid(x);
        zhDao.save(zh);
        secretGuard secretGuard=new secretGuard();
        secretGuard.setSecretGuard(loginPojo.getSecret());
        secretGuard.setAnswer(loginPojo.getAnswer());
        secretGuard.setUid(x);
        secretGuard.setUsername(username);
        secretGuardDao.save(secretGuard);
        zc.setState("success");
        return zc;
    }



    //登录
    @PostMapping("/logIn")
    public zc logIn(@RequestBody signInPojo signInPojo){
        zc zc=new zc();
        String username= signInPojo.getUsername();
        String password= signInPojo.getPassword();
        User user1=userDao.findByUsername(username);

        if (user1.getState()!=1){
            zc.setState("blocked");
            return zc;
        }
        Integer uid = user1.getUid();
        String p1=user1.getPassword();
        if(p1.equals(password)){
            UserInfo userInfo = userInfoDao.findByUid(uid);
            if (userInfo==null){
                zc.setUid(uid);
                zc.setState("noInfo");
            }else {
                zc.setUid(uid);
                zc.setState("success");
                zc.setName(userInfo.getName());
                zc.setPicture(userInfo.getPicture());
            }
            return zc;
        }else {
            zc.setState("error");
        }
        return zc;
    }

    //修改密码
    @PostMapping("/modifyPassword")
    public String modifyPassword(@RequestBody secretGuardPojo secretGuardPojo){
        secretGuard secretGuard=secretGuardDao.findByUsername(secretGuardPojo.getUsername());
        String p1=secretGuardPojo.getAnswer();
        String p2=secretGuard.getAnswer();
        if(p1.equals(p2)){
            userDao.updateUserPassword(secretGuardPojo.getNewPassword(),secretGuardPojo.getUsername());
            return "success";
        }else {
            return "error";
        }
    }

}
