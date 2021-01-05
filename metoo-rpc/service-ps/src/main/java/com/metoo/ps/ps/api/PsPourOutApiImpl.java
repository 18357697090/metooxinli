package com.metoo.ps.ps.api;

import com.loongya.core.util.RE;
import com.metoo.api.ps.PsCapsuleApi;
import com.metoo.api.ps.PsPourOutApi;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.pojo.old.model.PourOutCapsulePojo;
import com.metoo.pojo.old.model.PourOutPojo;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.ps.ps.service.PsCapsuleService;
import com.metoo.ps.ps.service.PsPourOutService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 心理倾诉师表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class PsPourOutApiImpl implements PsPourOutApi {

    @Autowired
    private PsPourOutService psPourOutService;

    @Autowired
    private PsCapsuleService psCapsuleService;

    @DubboReference
    private TjUserInfoApi tjUserInfoApi;

    @Autowired
    private PsCapsuleApi psCapsuleApi;

    @Override
    public RE capsule() {
        PourOutPojo pourOutPojo = new PourOutPojo();
        Pageable pageable = PageRequest.of(0,5, Sort.Direction.DESC,"prices");
        pourOutPojo.setPourOuts(psPourOutService.findByOnLine(1,pageable));
        List<Object[]> capsule= psCapsuleService.findCapsule();
        pourOutPojo.setPourOutCapsulePojos(this.tool(capsule));
        return RE.ok(pourOutPojo);
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
                        pourOutCapsulePojo.setCreateTime(c);
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
            pourOutCapsulePojo.setPicture(userInfo.getHeadImg());
            pourOutCapsulePojos.add(pourOutCapsulePojo);
        }
        return pourOutCapsulePojos;
    }
}
