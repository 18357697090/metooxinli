package com.metoo.web.controller.backup;

import com.metoo.metoo.entity.*;
import com.metoo.metoo.pojo.*;
import com.metoo.metoo.pojo.LoginPojo;
import com.metoo.metoo.repository.*;
import com.metoo.metoo.tools.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Api(tags={"用户相关接口"})
public class UserHandler {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private secretGuardDao secretGuardDao;
    @Autowired
    private ZhDao zhDao;


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

    //密保问题
    @GetMapping("/findSecretGuard")
    public String findSecretGuard(String username){
        secretGuard a= secretGuardDao.findByUsername(username);
        if(a==null){
            return "asd!!@@##";
        }
        return a.getSecretGuard();
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


    //获取个人账户信息
    @GetMapping("/findzh")
    public Zh findzh(Integer uid){
        return zhDao.findByUid(uid);
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






//    //上传修改头像
//    @PostMapping("/sctx")
//    public String sctx(@RequestParam(name="file") MultipartFile file) throws IOException {
//        String path = "F:\\test.jpg";
//        File file1 = new File(path);
//
//        FileUtils.copyInputStreamToFile(file.getInputStream(), file1);
//        Thumbnails.of(path).scale(1f).outputQuality(0.25f).toFile("F:\\test1.jpg");
//        String x= "data:image/png;base64,"+ Base64.encode(file.getBytes());
//        String a= Base64Tool.resizeImageTo40K(x);
//        test test = new test();
//        test.setPicture(a);
//        testDao.save(test);
//        return x;
//    }

//    //测试
//    @RequestMapping("/test")
//    public String test(@RequestParam(name="file") MultipartFile file) throws Exception {
//        String a= "data:image/png;base64,"+ Base64.encode(file.getBytes());
//        String x= Base64yasuo.resizeImageTo40K(a);
//        System.out.println(x);
//        return x;
//    }

//    @RequestMapping("/test")
//    public String test(@RequestBody testPojo file) throws Exception {
//        System.out.println(file);
//        List<String> file1= file.getStrings();
//        for (String s : file1) {
//            String a = s.substring(23);
//            byte[] bytes = a.getBytes();
//            byte[] bytes1 = ImageUtil.compressPhotoByQuality(bytes, 1f, 50);
//            String x = "data:image/jpg;base64," + Base64.encodeBase64String(bytes1);
//            test test = new test();
//            test.setPicture(x);
//            testDao.save(test);
//        }
//
//        return "a";
//    }
//


}
