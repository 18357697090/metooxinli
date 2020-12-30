package com.metoo.ps.ps.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.order.PsUserBuyCapsuleApi;
import com.metoo.api.ps.PsCapsuleApi;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.pojo.old.model.FindCapsuleByIdPojo;
import com.metoo.pojo.old.model.PourOutCapsulePojo;
import com.metoo.pojo.old.model.SaveCapsulePojo;
import com.metoo.pojo.order.model.PsUserBuyCapsuleModel;
import com.metoo.pojo.ps.model.PsCapsuleModel;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.ps.ps.dao.entity.PsCapsule;
import com.metoo.ps.ps.service.PsCapsuleService;
import com.metoo.tools.CreateID;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 心理倾诉胶囊（备忘录功能）表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
public class PsCapsuleApiImpl implements PsCapsuleApi {

    @Autowired
    private PsCapsuleService psCapsuleService;

    @DubboReference
    private TjUserInfoApi tjUserInfoApi;
    @DubboReference
    private PsUserBuyCapsuleApi psUserBuyCapsuleApi;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public PsCapsuleModel findByCapsuleId(Integer capsuleId) {
        PsCapsule pojo = psCapsuleService.findByCapsuleId(capsuleId);
        if(OU.isBlack(pojo)){
            return null;
        }
        PsCapsuleModel model = mapper.map(pojo, PsCapsuleModel.class);

        return model;

    }

    @Override
    public RE myCapsule(Integer uid, Integer page) {

        List<Object[]> capsules=psCapsuleService.findmyCapsules(uid,page);
        return RE.ok(this.tool(capsules));
    }

    @Override
    public RE modifyCapsule(Integer state, Integer capsuleId) {

        //state  0表示隐藏  1表示可见状态  2表示删除胶囊
        int x=0;
        switch (state){
            case 0:
                x = psCapsuleService.updataAttribute(0,capsuleId);
                break;
            case 1:
                x = psCapsuleService.updataAttribute(1,capsuleId);
                break;
            case 2:
                x = psCapsuleService.updataState(capsuleId);
                break;
            default:
                break;
        }
        if(x == 1){
            return RE.ok("success");
        }else {
            return RE.fail("error");
        }
    }

    @Override
    public RE capsuleDetail(Integer page) {
        page=page*7;
        List<Object[]> capsules=psCapsuleService.findCapsules(page);
        return RE.ok(this.tool(capsules));
    }

    @Override
    public RE saveCapsule(SaveCapsulePojo saveCapsulePojo, Integer uid) {
        if(uid==null){
            return RE.fail("error");
        }
        int capsuleId = CreateID.create();
        PsCapsule capsule1=psCapsuleService.findByCapsuleId(capsuleId);
        while (capsule1!=null){
            capsuleId=CreateID.create();
            capsule1=psCapsuleService.findByCapsuleId(capsuleId);
        }
        PsCapsule capsule = new PsCapsule();
        capsule.setState(1);
        capsule.setCapsuleId(capsuleId);
        capsule.setAttribute(saveCapsulePojo.getAttribute());
        capsule.setContent(saveCapsulePojo.getContent());
        capsule.setTitle(saveCapsulePojo.getTitle());
        capsule.setPrices(saveCapsulePojo.getPrices().intValue());
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
        psCapsuleService.save(capsule);
        return RE.ok();
    }

    @Override
    public RE capsule(Integer capsuleId, Integer uid) {

        PsCapsule capsule = psCapsuleService.findByCapsuleId(capsuleId);
        FindCapsuleByIdPojo findCapsuleByIdPojo = new FindCapsuleByIdPojo();
        int x = capsule.getUid();
        TjUserInfoModel userInfo = tjUserInfoApi.findByUid(x);
        findCapsuleByIdPojo.setName(userInfo.getName());
        findCapsuleByIdPojo.setPicture(userInfo.getPicture());
        capsule.setId(1L);
        PsUserBuyCapsuleModel userBuyCapsule = psUserBuyCapsuleApi.findByUidAndCapsuleId(uid,capsuleId);
        if(userBuyCapsule!=null){
            capsule.setUid(null);
            findCapsuleByIdPojo.setState("me");
            findCapsuleByIdPojo.setCapsule(mapper.map(capsule, PsCapsuleModel.class));
            return RE.ok(findCapsuleByIdPojo);
        }
        capsule.setUid(null);
        if(uid.equals(capsule.getUid())){
            findCapsuleByIdPojo.setState("me");
        }else {
            BigDecimal bigDecimal = new BigDecimal(0);
            int y=capsule.getPrices().compareTo(bigDecimal.intValue());
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
                findCapsuleByIdPojo.setCapsule(mapper.map(capsule, PsCapsuleModel.class));
                return RE.ok(findCapsuleByIdPojo);
            }
        }
        findCapsuleByIdPojo.setCapsule(mapper.map(capsule, PsCapsuleModel.class));
        return RE.ok(findCapsuleByIdPojo);
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
            TjUserInfoModel userInfo = tjUserInfoApi.findByUid(pourOutCapsulePojo.getUid());
            pourOutCapsulePojo.setUid(null);
            pourOutCapsulePojo.setName(userInfo.getName());
            pourOutCapsulePojo.setPicture(userInfo.getPicture());
            pourOutCapsulePojos.add(pourOutCapsulePojo);
        }
        return pourOutCapsulePojos;
    }
}
