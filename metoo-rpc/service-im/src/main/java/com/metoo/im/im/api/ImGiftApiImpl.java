package com.metoo.im.im.api;


import com.loongya.core.util.RE;
import com.metoo.api.im.ImGiftApi;
import com.metoo.im.im.service.ImGiftService;
import com.metoo.pojo.old.vo.ReturnGivingGiftDTO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 礼物表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public class ImGiftApiImpl implements ImGiftApi {

    @Autowired
    private ImGiftService imGiftService;

    @Override
    public RE givingGift(Integer uid, Integer acceptedId, Integer giftId, String number) {
        ReturnGivingGiftDTO returnGivingGiftDTO = new ReturnGivingGiftDTO();
        Zh zh = zhDao.findByUid(uid);
        Gift gift = giftDao.findByGiftId(giftId);
        BigDecimal number1= new BigDecimal(number);
        BigDecimal balance = zh.getBalance().subtract(gift.getPrices().multiply(number1));
        if (balance.compareTo(BigDecimal.ZERO)>=0){
            zhDao.updateBalance(balance,uid);
            GiftRecord giftRecord = new GiftRecord();
            giftRecord.setUid(uid);
            giftRecord.setAccepted(acceptedId);
            giftRecord.setGiftId(giftId);
            giftRecordDao.save(giftRecord);
            ZhRecord zhRecord = new ZhRecord();
            zhRecord.setUid(uid);
            zhRecord.setPrices(gift.getPrices());
            zhRecord.setType("刷礼物");
            zhRecord.setContent(gift.getName());
            zhRecordDao.save(zhRecord);
            returnGivingGiftDTO.setBalance(balance);
            returnGivingGiftDTO.setState("success");
            returnGivingGiftDTO.setExplain("送礼物成功");
            return returnGivingGiftDTO;
        }else {
            returnGivingGiftDTO.setBalance(zh.getBalance());
            returnGivingGiftDTO.setState("error");
            returnGivingGiftDTO.setExplain("账户余额不足");
            return returnGivingGiftDTO;
        }
        return null;
    }

    @Override
    public RE getGiftList() {
        return null;
    }
}
