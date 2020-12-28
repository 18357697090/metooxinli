package com.metoo.web.controller.backup;

import com.loongya.core.util.RE;
import com.metoo.metoo.DTO.ReturnGivingGiftDTO;
import com.metoo.metoo.entity.*;
import com.metoo.metoo.repository.*;
import com.tencentyun.TLSSigAPIv2;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/audioroom")
@Api(tags={"聊天室相关接口"})
public class AudioRoomHandler {

    private final TLSSigAPIv2 api = new TLSSigAPIv2(
            1400421634, "60307e516ff42d72a4d260873358c43054c7f08b3a5274b8dcce12019c58d5f1");

    @Autowired
    private UserSigDao userSigDao;
    @Autowired
    private GiftDao giftDao;
    @Autowired
    private ZhDao zhDao;
    @Autowired
    private ZhRecordDao zhRecordDao;
    @Autowired
    private GiftRecordDao giftRecordDao;

    @GetMapping("/getusersig")
    public String getusersig(@RequestHeader("UID") Integer uid){
        UserSig userSig=userSigDao.findByUid(uid);
        String identifier = ""+uid;
        if(userSig==null){
            UserSig userSig1=new UserSig();
            String usersig= api.genSig(identifier, 30*86400);
            userSig1.setUid(uid);
            userSig1.setUsersig(usersig);
             userSigDao.save(userSig1);
            return usersig;
        }else {
            long a = userSig.getUpdatetime().getTime();
            Date date=new Date();
            long b=date.getTime()-a;
            long c= 2292000000L;
            if(b>c){
                String usersig= api.genSig(identifier, 30*86400);
                Date date1=new Date();
                userSigDao.updatausersig(usersig,date1,uid);
                return usersig;
            }else {
                return userSig.getUsersig();
            }
        }
    }

    @GetMapping("/givingGift")
    public ReturnGivingGiftDTO givingGift(@RequestHeader("UID")Integer uid, Integer acceptedId, Integer giftId,String number){
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

    @GetMapping("/getGiftList")
    public List<Gift> getGiftList(){
        return giftDao.findAll();
    }






}
