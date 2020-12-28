package com.metoo.tools;


import com.metoo.metoo.DTO.ReturnGivingGiftDTO;
import com.metoo.metoo.entity.*;
import com.metoo.metoo.entity1.UserMessage;
import com.metoo.metoo.entity1.saveUserMessage;
import com.metoo.metoo.psychology.Options;
import com.metoo.metoo.psychology.Problem;
import com.metoo.metoo.psychologyDao.OptionsDao;
import com.metoo.metoo.psychologyDao.ProblemDao;
import com.metoo.metoo.repository.*;
import com.metoo.metoo.repository1.UserMessageDao;
import com.metoo.metoo.repository1.saveUserMessageDao;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@Component
public class Repository {

    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private OptionsDao optionsDao;
    @Autowired
    private ProblemDao problemDao;
    @Autowired
    private UserMessageDao userMessageDao;
    @Autowired
    private saveUserMessageDao saveUserMessageDao;
    @Autowired
    private Mapper mapper;
    @Autowired
    private AudioRoomChatRecordDao audioRoomChatRecordDao;
    @Autowired
    private ZhDao zhDao;
    @Autowired
    private GiftDao giftDao;
    @Autowired
    private GiftRecordDao giftRecordDao;
    @Autowired
    private ZhRecordDao zhRecordDao;
    @Autowired
    private FriendDao friendDao;

    public static Repository repository;


    @PostConstruct
    public void init() {
        repository = this;
    }

    /**                          聊天室                             */

    //处理聊天室appMessage信息，转AudioRoomMessage
    public AudioRoomMessage handleAppMessage(AppMessage appMessage){
        return repository.mapper.map(appMessage,AudioRoomMessage.class);
    }

    public AudioRoomChatMessage HandleAudioRoomChatMessage(AppMessage appMessage){
        return repository.mapper.map(appMessage,AudioRoomChatMessage.class);
    }

    //送礼物
    public ReturnGivingGiftDTO givingGift(Integer uid, Integer acceptedId, Integer giftId, String number){
        ReturnGivingGiftDTO returnGivingGiftDTO = new ReturnGivingGiftDTO();
        Zh zh = repository.zhDao.findByUid(uid);
        Gift gift = repository.giftDao.findByGiftId(giftId);
        returnGivingGiftDTO.setGift(gift);
        BigDecimal number1= new BigDecimal(number);
        BigDecimal balance = zh.getBalance().subtract(gift.getPrices().multiply(number1));
        if (balance.compareTo(BigDecimal.ZERO)>=0){
            repository.zhDao.updateBalance(balance,uid);
            GiftRecord giftRecord = new GiftRecord();
            giftRecord.setUid(uid);
            giftRecord.setAccepted(acceptedId);
            giftRecord.setGiftId(giftId);
            repository.giftRecordDao.save(giftRecord);
            ZhRecord zhRecord = new ZhRecord();
            zhRecord.setUid(uid);
            zhRecord.setPrices(gift.getPrices());
            zhRecord.setType("刷礼物");
            zhRecord.setContent(gift.getName());
            repository.zhRecordDao.save(zhRecord);
            returnGivingGiftDTO.setBalance(balance);
            returnGivingGiftDTO.setState("success");
            returnGivingGiftDTO.setExplain("送礼物成功");
        }else {
            returnGivingGiftDTO.setBalance(zh.getBalance());
            returnGivingGiftDTO.setState("error");
            returnGivingGiftDTO.setExplain("账户余额不足");
        }
        return returnGivingGiftDTO;
    }

    //保存聊天室信息
    public void saveAudioRoomChatRecord(Integer audioRoomId,String content){
        AudioRoomChatRecord audioRoomChatRecord = new AudioRoomChatRecord();
        audioRoomChatRecord.setAudioRoomId(audioRoomId);
        audioRoomChatRecord.setContent(content);
        repository.audioRoomChatRecordDao.save(audioRoomChatRecord);
    }

    /**                          测量                             */


    public Options findOptions(int scaleId){
        return repository.optionsDao.findByScaleId(scaleId);
    }

    public List<Problem> findProblem(int scaleId){
        return repository.problemDao.findByScaleId(scaleId);
    }

    /**                          用户                             */
    //获取个人信息
    public UserInfo findUserInfo(Integer uid){
        return repository.userInfoDao.findByUid(uid);
    }





    /**                          好友聊天                             */

    //查看是否为好友
    public Friend checkFriendShip(Integer uid,Integer firendId){
        return repository.friendDao.findByUidAndFriendId(uid,firendId);
    }

    //存所有消息
    public void saveUserMessage(Integer uid,Integer sendId,String message){
        saveUserMessage saveUserMessage = new saveUserMessage();
        saveUserMessage.setUid(uid);
        saveUserMessage.setSendId(sendId);
        saveUserMessage.setMessage(message);
        repository.saveUserMessageDao.save(saveUserMessage);
    }

    //存离线消息
    public void saveUserMessageOffOline(Integer uid,Integer sendId,String message){
        UserMessage userMessage = new UserMessage();
        userMessage.setUid(uid);
        userMessage.setSendId(sendId);
        userMessage.setMessage(message);
        userMessage.setState(1);
        repository.userMessageDao.save(userMessage);
    }

    public List<UserMessage> takeOffLineMessage(Integer uid){
        return repository.userMessageDao.uid(uid);
    }

    public void modifyOfflineMessage(Integer uid){
        repository.userMessageDao.updateState(uid);
    }

    public void deleteMessage(Integer uid){
        repository.userMessageDao.deleteByUid(uid);
    }


    //修改在不在线
    public void userChangeOnline(Integer uid){

    }


}
