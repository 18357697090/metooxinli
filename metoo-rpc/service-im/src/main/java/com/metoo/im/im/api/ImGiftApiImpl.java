package com.metoo.im.im.api;


import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.im.ImGiftApi;
import com.metoo.api.order.ImGiftRecordApi;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.api.tj.TjUserAccountDetailApi;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.im.im.dao.entity.ImGift;
import com.metoo.im.im.service.ImGiftService;
import com.metoo.pojo.old.vo.ReturnGivingGiftDTO;
import com.metoo.pojo.order.model.ImGiftRecordModel;
import com.metoo.pojo.tj.model.TjUserAccountDetailModel;
import com.metoo.pojo.tj.model.TjUserAccountModel;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 礼物表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
public class ImGiftApiImpl implements ImGiftApi {

    @Autowired
    private ImGiftService imGiftService;

    @DubboReference
    private TjUserAccountApi tjUserAccountApi;
    @DubboReference
    private TjUserAccountDetailApi tjUserAccountDetailApi;

    @DubboReference
    private ImGiftRecordApi imGiftRecordApi;

    @Override
    public RE givingGift(Integer uid, Integer acceptedId, Integer giftId, String number) {
        ReturnGivingGiftDTO returnGivingGiftDTO = new ReturnGivingGiftDTO();
        TjUserAccountModel tjUserAccountModel = tjUserAccountApi.findByUid(uid);
        ImGift gift = imGiftService.findByGiftId(giftId);
        BigDecimal number1= new BigDecimal(number);
        BigDecimal balance = tjUserAccountModel.getBalance().subtract(gift.getPrices().multiply(number1));
        if (balance.compareTo(BigDecimal.ZERO)>=0){
            tjUserAccountApi.updateBalance(balance,uid);

            ImGiftRecordModel giftRecord = new ImGiftRecordModel();
            giftRecord.setUid(uid);
            giftRecord.setAccepted(acceptedId);
            giftRecord.setGiftId(giftId);
            imGiftRecordApi.save(giftRecord);
            TjUserAccountDetailModel zhRecord = new TjUserAccountDetailModel();
            zhRecord.setUid(uid);
            zhRecord.setPrices(gift.getPrices().intValue());
            zhRecord.setType("刷礼物");
            zhRecord.setContent(gift.getName());
            tjUserAccountDetailApi.save(zhRecord);
            returnGivingGiftDTO.setBalance(balance);
            returnGivingGiftDTO.setState("success");
            returnGivingGiftDTO.setExplain("送礼物成功");
            if (OU.isBlack(returnGivingGiftDTO)){
                return RE.noData();
            }
            return RE.ok(returnGivingGiftDTO);
        }else {
            returnGivingGiftDTO.setBalance(tjUserAccountModel.getBalance());
            returnGivingGiftDTO.setState("error");
            returnGivingGiftDTO.setExplain("账户余额不足");
            if (OU.isBlack(returnGivingGiftDTO)){
                return RE.noData();
            }
            return RE.ok(returnGivingGiftDTO);
        }
    }

    @Override
    public RE getGiftList() {
        List<ImGift> imGifts = imGiftService.findAll();
        if(OU.isBlack(imGifts)){
            return RE.noData();
        }
        return RE.ok(imGifts);
    }

    @Override
    public RE findByGiftId(Integer giftId) {
        ImGift imGift = imGiftService.findByGiftId(giftId);
        if(OU.isBlack(imGift)){
            return RE.noData();
        }
        return RE.ok(imGift);
    }
}
