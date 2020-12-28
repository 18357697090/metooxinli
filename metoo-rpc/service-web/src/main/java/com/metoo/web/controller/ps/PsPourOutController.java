package com.metoo.web.controller.ps;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 心理倾诉师表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/ps-pour-out")
public class PsPourOutController {

    @GetMapping("/capsule")
    public PourOutPojo capsule(){
        PourOutPojo pourOutPojo = new PourOutPojo();
        Pageable pageable = PageRequest.of(0,5, Sort.Direction.DESC,"prices");
        pourOutPojo.setPourOuts(pourOutDao.findByOnLine(1,pageable));
        List<Object[]> capsule= capsuleDao.findCapsule();
        pourOutPojo.setPourOutCapsulePojos(this.tool(capsule));
        return pourOutPojo;
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
