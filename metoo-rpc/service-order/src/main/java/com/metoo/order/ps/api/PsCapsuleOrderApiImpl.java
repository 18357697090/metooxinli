package com.metoo.order.ps.api;

import com.loongya.core.util.RE;
import com.metoo.api.order.PsCapsuleOrderApi;
import com.metoo.api.ps.PsCapsuleApi;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.order.ps.dao.entity.PsCapsuleOrder;
import com.metoo.order.ps.service.PsCapsuleOrderService;
import com.metoo.pojo.order.model.PsCapsuleOrderModel;
import com.metoo.pojo.ps.model.PsCapsuleModel;
import com.metoo.pojo.tj.model.TjUserAccountModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class PsCapsuleOrderApiImpl implements PsCapsuleOrderApi {

    @DubboReference
    private TjUserAccountApi tjUserAccountApi;

    @DubboReference
    private PsCapsuleApi psCapsuleApi;

    @Autowired
    private PsCapsuleOrderService psCapsuleOrderService;

    @Override
    public RE pay(Integer uid, Integer capsuleId) {

        TjUserAccountModel zh = tjUserAccountApi.findByUid(uid);
        PsCapsuleModel capsule = psCapsuleApi.findByCapsuleId(capsuleId);
        BigDecimal prices = new BigDecimal(capsule.getPrices());
        int x = zh.getBalance().compareTo(prices);
        if(x >= 0){
            tjUserAccountApi.updateBalance(zh.getBalance().subtract(prices),uid);
            PsCapsuleOrder userBuyCapsule = new PsCapsuleOrder();
            userBuyCapsule.setCapsuleId(capsuleId);
            userBuyCapsule.setUid(uid);
            psCapsuleOrderService.save(userBuyCapsule);
            return RE.ok();
        }else {
            return RE.fail("error");
        }
    }

    @Override
    public PsCapsuleOrderModel findByUidAndCapsuleId(Integer uid, Integer capsuleId) {

        return psCapsuleOrderService.findByUidAndCapsuleId(uid, capsuleId);
    }
}
