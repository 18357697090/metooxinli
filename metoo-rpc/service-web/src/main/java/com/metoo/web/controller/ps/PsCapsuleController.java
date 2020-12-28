package com.metoo.web.controller.ps;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 心理倾诉胶囊（备忘录功能）表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/ps-capsule")
public class PsCapsuleController {
    @GetMapping("/mycapsule")
    public List<PourOutCapsulePojo> mycapsule(@RequestHeader("UID") Integer uid, Integer page){
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


}
