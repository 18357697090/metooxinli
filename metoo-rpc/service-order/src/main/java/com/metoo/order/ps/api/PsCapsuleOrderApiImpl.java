package com.metoo.order.ps.api;

import com.loongya.core.util.ConstantUtil;
import com.loongya.core.util.RE;
import com.metoo.api.order.PsCapsuleOrderApi;
import com.metoo.api.ps.PsCapsuleApi;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.api.tj.TjUserAccountCoinDetailApi;
import com.metoo.api.tj.TjUserAccountDetailApi;
import com.metoo.order.ps.dao.entity.PsCapsuleOrder;
import com.metoo.order.ps.service.PsCapsuleOrderService;
import com.metoo.pojo.order.model.PsCapsuleOrderModel;
import com.metoo.pojo.ps.model.PsCapsuleModel;
import com.metoo.pojo.ps.vo.PsCapsuleVo;
import com.metoo.pojo.tj.model.TjUserAccountCoinDetailModel;
import com.metoo.pojo.tj.model.TjUserAccountDetailAddDetailModel;
import com.metoo.pojo.tj.model.TjUserAccountModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

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
    private TjUserAccountDetailApi tjUserAccountDetailApi;

    @DubboReference
    private PsCapsuleApi psCapsuleApi;

    @Autowired
    private PsCapsuleOrderService psCapsuleOrderService;


    @DubboReference
    private TjUserAccountCoinDetailApi tjUserAccountCoinDetailApi;

    @Override
    public RE pay(PsCapsuleVo vo) {
        TjUserAccountModel accountModel = tjUserAccountApi.findByUid(vo.getUserId());
        PsCapsuleModel capsule = psCapsuleApi.findByCapsuleId(vo.getCapsuleId());
        if(accountModel.getBalance().compareTo(capsule.getPrice()) >= 0){
            tjUserAccountApi.updateBalance(capsule.getPrice(),vo.getUserId());
            // 明细添加  todo. need asyn
            TjUserAccountDetailAddDetailModel acModel = new TjUserAccountDetailAddDetailModel();
            acModel.setUid(vo.getUserId());
            acModel.setRemark("购买胶囊支出兔币");
            acModel.setContent("购买胶囊,支出" + capsule.getPrice() + "兔币" + ", 购买胶囊id:{" + capsule.getId() + "}" + "购买胶囊标题: {" + capsule.getTitle() + "}");
            acModel.setPrice(capsule.getPrice());
            acModel.setAccountId(accountModel.getId());
            acModel.setType(ConstantUtil.TjUserAccountDetailTypeEnum.GIVE_CAPSULE.getCode());
            acModel.setTypeName(ConstantUtil.TjUserAccountDetailTypeEnum.GIVE_CAPSULE.getMsg());
            acModel.setAfterPrice(accountModel.getBalance().subtract(capsule.getPrice()));
            acModel.setPrePrice(accountModel.getBalance());
            tjUserAccountDetailApi.insertDetails(acModel);
            PsCapsuleOrder userBuyCapsule = new PsCapsuleOrder();
            userBuyCapsule.setCapsuleId(vo.getCapsuleId());
            userBuyCapsule.setUid(vo.getUserId());
            userBuyCapsule.setCreateTime(new Date());
            psCapsuleOrderService.save(userBuyCapsule);
            return RE.ok();
        }else {
            return RE.fail("余额不足");
        }
    }

    @Override
    public RE payByCoin(PsCapsuleVo vo) {
        // 判断用户心理币是否充足
        TjUserAccountModel accountModel =tjUserAccountApi.findByUid(vo.getUserId());
        PsCapsuleModel capsule = psCapsuleApi.findByCapsuleId(vo.getCapsuleId());
        if(accountModel.getPsCoin().compareTo(capsule.getPrice())<0){
            return RE.fail("心理币不足");
        }
        // 减心理币
        tjUserAccountApi.updateBalance(capsule.getPrice(),vo.getUserId());
        // 明细添加  todo. need asyn
        TjUserAccountCoinDetailModel acModel = new TjUserAccountCoinDetailModel();
        acModel.setUid(vo.getUserId());
        acModel.setRemark("购买胶囊支出心理币");
        acModel.setContent("购买胶囊,支出" + capsule.getPrice() + "心理币" + ", 购买胶囊id:{" + capsule.getId() + "}" + "购买胶囊标题: {" + capsule.getTitle() + "}");
        acModel.setPrice(capsule.getPrice());
        acModel.setAccountId(accountModel.getId());
        acModel.setType(ConstantUtil.TjUserAccountDetailTypeEnum.GIVE_CAPSULE.getCode());
        acModel.setTypeName(ConstantUtil.TjUserAccountDetailTypeEnum.GIVE_CAPSULE.getMsg());
        acModel.setAfterPrice(accountModel.getPsCoin().subtract(capsule.getPrice()));
        acModel.setPrePrice(accountModel.getPsCoin());
        tjUserAccountCoinDetailApi.insertDetails(acModel);
        PsCapsuleOrder userBuyCapsule = new PsCapsuleOrder();
        userBuyCapsule.setCapsuleId(vo.getCapsuleId());
        userBuyCapsule.setUid(vo.getUserId());
        userBuyCapsule.setCreateTime(new Date());
        psCapsuleOrderService.save(userBuyCapsule);
        return RE.ok();
    }

    @Override
    public PsCapsuleOrderModel findByUidAndCapsuleId(Integer uid, Integer capsuleId) {

        return psCapsuleOrderService.findByUidAndCapsuleId(uid, capsuleId);
    }
}
