package com.metoo.order.ps.api;

import com.loongya.core.util.RE;
import com.metoo.api.order.PsUserBuyCapsuleApi;
import com.metoo.api.ps.PsCapsuleApi;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.order.ps.dao.entity.PsUserBuyCapsule;
import com.metoo.order.ps.service.PsUserBuyCapsuleService;
import com.metoo.pojo.order.model.PsUserBuyCapsuleModel;
import com.metoo.pojo.ps.model.PsCapsuleModel;
import com.metoo.pojo.tj.model.TjUserAccountModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * <p>
 * 用户购买胶囊记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
public class PsUserBuyCapsuleApiImpl implements PsUserBuyCapsuleApi {

    @DubboReference
    private TjUserAccountApi tjUserAccountApi;

    @DubboReference
    private PsCapsuleApi psCapsuleApi;

    @Autowired
    private PsUserBuyCapsuleService psUserBuyCapsuleService;

    @Override
    public RE pay(Integer uid, Integer capsuleId) {

        TjUserAccountModel zh = tjUserAccountApi.findByUid(uid);
        PsCapsuleModel capsule = psCapsuleApi.findByCapsuleId(capsuleId);
        BigDecimal prices = new BigDecimal(capsule.getPrices());
        int x = zh.getBalance().compareTo(prices);
        if(x >= 0){
            tjUserAccountApi.updateBalance(zh.getBalance().subtract(prices),uid);
            PsUserBuyCapsule userBuyCapsule = new PsUserBuyCapsule();
            userBuyCapsule.setCapsuleId(capsuleId);
            userBuyCapsule.setUid(uid);
            psUserBuyCapsuleService.save(userBuyCapsule);
            return RE.ok();
        }else {
            return RE.serviceFail("error");
        }
    }

    @Override
    public PsUserBuyCapsuleModel findByUidAndCapsuleId(Integer uid, Integer capsuleId) {

        return psUserBuyCapsuleService.findByUidAndCapsuleId(uid, capsuleId);
    }
}
