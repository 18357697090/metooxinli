package com.metoo.web.controller.im;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 礼物表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/im/im-gift")
public class ImGiftController {

    @ApiOperation("送礼物")
    @GetMapping("/givingGift")
    public ReturnGivingGiftDTO givingGift(@RequestHeader("UID")Integer uid, Integer acceptedId, Integer giftId, String number){
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
    }

    @ApiOperation("获取礼物列表")
    @GetMapping("/getGiftList")
    public List<Gift> getGiftList(){
        return giftDao.findAll();
    }


}
