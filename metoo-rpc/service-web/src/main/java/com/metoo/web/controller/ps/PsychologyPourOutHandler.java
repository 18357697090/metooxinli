package com.metoo.web.controller.ps;

import com.metoo.metoo.entity.UserInfo;
import com.metoo.metoo.entity.Zh;
import com.metoo.metoo.pojo.*;
import com.metoo.metoo.psychology.Capsule;
import com.metoo.metoo.psychology.UserBuyCapsule;
import com.metoo.metoo.psychologyDao.CapsuleDao;
import com.metoo.metoo.psychologyDao.PourOutDao;
import com.metoo.metoo.psychologyDao.UserBuyCapsuleDao;
import com.metoo.metoo.repository.UserInfoDao;
import com.metoo.metoo.repository.ZhDao;
import com.metoo.metoo.tools.createID;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Pageable pageable= PageRequest.of(0,10, Sort.Direction.DESC,"beWatched");

@RestController
@RequestMapping("/pourout")
@Api(tags={"心理倾诉相关接口"})
public class PsychologyPourOutHandler {

    @Autowired
    private CapsuleDao capsuleDao;
    @Autowired
    private PourOutDao pourOutDao;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private ZhDao zhDao;
    @Autowired
    private UserBuyCapsuleDao userBuyCapsuleDao;

    @GetMapping("/mycapsule")
    public List<PourOutCapsulePojo> mycapsule(@RequestHeader("UID") Integer uid,Integer page){
        List<Object[]> capsules=capsuleDao.findmyCapsules(uid,page);
        return this.tool(capsules);
    }

    @GetMapping("/modifyacapsule")
    public String modifyCapsule(Integer state,Integer capsuleId){
        //state  0表示隐藏  1表示可见状态  2表示删除胶囊
        if(state==0){
            int x = capsuleDao.updataAttribute(0,capsuleId);
            if (x==1){
                return "success";
            }else {
                return "error";
            }
        }else if(state==1){
            int x = capsuleDao.updataAttribute(1,capsuleId);
            if (x==1){
                return "success";
            }else {
                return "error";
            }
        }else if(state==2){
            int x = capsuleDao.updataState(capsuleId);
            if (x==1){
                return "success";
            }else {
                return "error";
            }
        }else {
            return "error";
        }

    }

    @GetMapping("/capsuleDetail")
    public List<PourOutCapsulePojo> capsuleDetail(Integer page){
//        Pageable pageable = PageRequest.of(page,7, Sort.Direction.DESC,"id");
        page=page*7;
        List<Object[]> capsules=capsuleDao.findCapsules(page);
        return this.tool(capsules);

    }

    @GetMapping("/capsule")
    public PourOutPojo capsule(){
        PourOutPojo pourOutPojo = new PourOutPojo();
        Pageable pageable = PageRequest.of(0,5, Sort.Direction.DESC,"prices");
        pourOutPojo.setPourOuts(pourOutDao.findByOnLine(1,pageable));
        List<Object[]> capsule= capsuleDao.findCapsule();
        pourOutPojo.setPourOutCapsulePojos(this.tool(capsule));
        return pourOutPojo;
    }

    @ApiOperation("发布胶囊")
    @PostMapping("/saveCapsule")
    public String saveCapsule(@RequestBody SaveCapsulePojo saveCapsulePojo,@RequestHeader("UID")Integer uid) {
        if(uid==null){
            return "error";
        }
        int capsuleId = createID.create();
        Capsule capsule1=capsuleDao.findByCapsuleId(capsuleId);
        while (capsule1!=null){
            capsuleId=createID.create();
            capsule1=capsuleDao.findByCapsuleId(capsuleId);
        }
        Capsule capsule = new Capsule();
        capsule.setState(1);
        capsule.setCapsuleId(capsuleId);
        capsule.setAttribute(saveCapsulePojo.getAttribute());
        capsule.setContent(saveCapsulePojo.getContent());
        capsule.setTitle(saveCapsulePojo.getTitle());
        capsule.setPrices(saveCapsulePojo.getPrices());
        capsule.setUid(uid);
        List<String> picture = saveCapsulePojo.getPicture();
        for(int i=0;i<picture.size();i++){
            switch (i){
                case 0 :
                    capsule.setPicture1(picture.get(i));
                    break;
                case 1 :
                    capsule.setPicture2(picture.get(i));
                    break;
                case 2 :
                    capsule.setPicture3(picture.get(i));
                    break;
                case 3 :
                    capsule.setPicture4(picture.get(i));
                    break;
                case 4 :
                    capsule.setPicture5(picture.get(i));
                    break;
                case 5 :
                    capsule.setPicture6(picture.get(i));
                    break;
                case 6 :
                    capsule.setPicture7(picture.get(i));
                    break;
                case 7 :
                    capsule.setPicture8(picture.get(i));
                    break;
                case 8 :
                    capsule.setPicture9(picture.get(i));
                    break;
            }
        }
        capsuleDao.save(capsule);
        return "success";
    }

    @GetMapping("/pay")
    public String pay(@RequestHeader("UID") Integer uid,Integer capsuleId){
        Zh zh = zhDao.findByUid(uid);
        Capsule capsule = capsuleDao.findByCapsuleId(capsuleId);
        BigDecimal prices = capsule.getPrices();
        int x = zh.getBalance().compareTo(prices);
        if(x >= 0){
            zhDao.updateBalance(zh.getBalance().subtract(prices),uid);
            UserBuyCapsule userBuyCapsule = new UserBuyCapsule();
            userBuyCapsule.setCapsuleId(capsuleId);
            userBuyCapsule.setUid(uid);
            userBuyCapsuleDao.save(userBuyCapsule);
            return "success";
        }else {
            return "error";
        }
    }

    @GetMapping("/findCapsuleById")
    public FindCapsuleByIdPojo capsule(int capsuleId,@RequestHeader("UID")Integer uid){
        Capsule capsule = capsuleDao.findByCapsuleId(capsuleId);
        FindCapsuleByIdPojo findCapsuleByIdPojo = new FindCapsuleByIdPojo();
        int x = capsule.getUid();
        UserInfo userInfo = userInfoDao.findByUid(x);
        findCapsuleByIdPojo.setName(userInfo.getName());
        findCapsuleByIdPojo.setPicture(userInfo.getPicture());
        capsule.setId(1);
        UserBuyCapsule userBuyCapsule = userBuyCapsuleDao.findByUidAndCapsuleId(uid,capsuleId);
        if(userBuyCapsule!=null){
            capsule.setUid(null);
            findCapsuleByIdPojo.setState("me");
            findCapsuleByIdPojo.setCapsule(capsule);
            return findCapsuleByIdPojo;
        }
        capsule.setUid(null);
        if(uid.equals(capsule.getUid())){
            findCapsuleByIdPojo.setState("me");
        }else {
            BigDecimal bigDecimal = new BigDecimal(0);
            int y=capsule.getPrices().compareTo(bigDecimal);
            if( y > 0){
                capsule.setContent(null);
                capsule.setPicture1(null);
                capsule.setPicture2(null);
                capsule.setPicture3(null);
                capsule.setPicture4(null);
                capsule.setPicture5(null);
                capsule.setPicture6(null);
                capsule.setPicture7(null);
                capsule.setPicture8(null);
                capsule.setPicture9(null);
                findCapsuleByIdPojo.setCapsule(capsule);
                return findCapsuleByIdPojo;
            }
        }
        findCapsuleByIdPojo.setCapsule(capsule);
        return findCapsuleByIdPojo;
    }





    private List<PourOutCapsulePojo> tool(List<Object[]> capsules){
        List<PourOutCapsulePojo> pourOutCapsulePojos = new ArrayList<>();
        for (Object[] objects : capsules) {
            PourOutCapsulePojo pourOutCapsulePojo = new PourOutCapsulePojo();
            String x = Arrays.toString(objects);
            String y = x.substring(1, x.length() - 1);
            String[] str = y.split(",");
            for (int j = 0; j < str.length; j++) {
                String b = str[j];
                String c = b;
                if (j != 0) {
                    c = b.substring(1);
                }
                switch (j) {
                    case 0:
                        pourOutCapsulePojo.setCapsuleId(Integer.parseInt(c));
                        break;
                    case 1:
                        pourOutCapsulePojo.setCreationTime(c);
                        break;
                    case 2:
                        pourOutCapsulePojo.setBeWatched(Integer.parseInt(c));
                        break;
                    case 3:
                        pourOutCapsulePojo.setPrices(new BigDecimal(c));
                        break;
                    case 4:
                        pourOutCapsulePojo.setTitle(c);
                        break;
                    case 5:
                        pourOutCapsulePojo.setUid(Integer.parseInt(c));
                        break;
                    case 6:
                        pourOutCapsulePojo.setAttribute(Integer.parseInt(c));
                        break;
                }
            }
            UserInfo userInfo = userInfoDao.findByUid(pourOutCapsulePojo.getUid());
            pourOutCapsulePojo.setUid(null);
            pourOutCapsulePojo.setName(userInfo.getName());
            pourOutCapsulePojo.setPicture(userInfo.getPicture());
            pourOutCapsulePojos.add(pourOutCapsulePojo);
        }
        return pourOutCapsulePojos;
    }

}
