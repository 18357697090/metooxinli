package com.metoo.wechat.config.tools;


import com.loongya.core.util.RE;
import com.metoo.api.im.*;
import com.metoo.api.order.ImGiftRecordApi;
import com.metoo.api.ps.PsScaleOptionsApi;
import com.metoo.api.ps.PsScaleProblemApi;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.api.tj.TjUserAccountDetailApi;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.pojo.im.model.*;
import com.metoo.pojo.old.model.TjUserInfoPojoModel;
import com.metoo.pojo.old.vo.ReturnGivingGiftDTO;
import com.metoo.pojo.order.model.ImGiftRecordModel;
import com.metoo.pojo.ps.model.PsScaleOptionsModel;
import com.metoo.pojo.ps.model.PsScaleProblemModel;
import com.metoo.pojo.tj.model.TjUserAccountDetailModel;
import com.metoo.pojo.tj.model.TjUserAccountModel;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.tools.AppMessage;
import com.metoo.tools.AudioRoomChatMessage;
import com.metoo.tools.AudioRoomMessage;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@Component
public class Repository {

    @DubboReference
    private TjUserInfoApi tjUserInfoApi;
    @DubboReference
    private PsScaleOptionsApi psScaleOptionsApi;
    @DubboReference
    private PsScaleProblemApi psProblemApi;
    @DubboReference
    private ImUserMessageApi imUserMessageApi;
    @DubboReference
    private ImSaveUserMessageApi imSaveUserMessageApi;
    @DubboReference
    private ImAudioRoomChatRecordApi imAudioRoomChatRecordApi;
    @DubboReference
    private TjUserAccountApi tjUserAccountApi;
    @DubboReference
    private ImGiftApi imGiftApi;
    @DubboReference
    private ImGiftRecordApi imGiftRecordApi;
    @DubboReference
    private TjUserAccountDetailApi tjUserAccountDetailApi;
    @DubboReference
    private ImFriendApi imFriendApi;
    @Autowired
    private Mapper mapper;

    public static Repository repository;


    @PostConstruct
    public void init() {
        repository = this;
    }

    /**                          ?????????                             */

    //???????????????appMessage????????????AudioRoomMessage
    public AudioRoomMessage handleAppMessage(AppMessage appMessage){
        return repository.mapper.map(appMessage,AudioRoomMessage.class);
    }

    public AudioRoomChatMessage HandleAudioRoomChatMessage(AppMessage appMessage){
        return repository.mapper.map(appMessage,AudioRoomChatMessage.class);
    }

    //?????????
    public ReturnGivingGiftDTO givingGift(Integer uid, Integer acceptedId, Integer giftId, String number){
        ReturnGivingGiftDTO returnGivingGiftDTO = new ReturnGivingGiftDTO();
        TjUserAccountModel zh = repository.tjUserAccountApi.findByUid(uid);

        RE gift = repository.imGiftApi.findByGiftId(giftId);
        ImGiftModel imGiftModel = mapper.map(gift.getData(),ImGiftModel.class);
        returnGivingGiftDTO.setGift(imGiftModel);
        BigDecimal number1= new BigDecimal(number);
        BigDecimal balance = zh.getBalance().subtract(imGiftModel.getPrices().multiply(number1));
        if (balance.compareTo(BigDecimal.ZERO)>=0){
            repository.tjUserAccountApi.updateBalance(imGiftModel.getPrices().multiply(number1),uid);
            ImGiftRecordModel imGiftRecordModel = new ImGiftRecordModel();
            imGiftRecordModel.setUid(uid);
            imGiftRecordModel.setAccepted(acceptedId);
            imGiftRecordModel.setGiftId(giftId);
            repository.imGiftRecordApi.save(imGiftRecordModel);
            TjUserAccountDetailModel tjUserAccountDetailModel = new TjUserAccountDetailModel();
            tjUserAccountDetailModel.setUid(uid);
            tjUserAccountDetailModel.setPrice(imGiftModel.getPrices());
            tjUserAccountDetailModel.setRemark("?????????");
            tjUserAccountDetailModel.setContent(imGiftModel.getName());
            repository.tjUserAccountDetailApi.save(tjUserAccountDetailModel);
            returnGivingGiftDTO.setBalance(balance);
            returnGivingGiftDTO.setState("success");
            returnGivingGiftDTO.setExplain("???????????????");
        }else {
            returnGivingGiftDTO.setBalance(zh.getBalance());
            returnGivingGiftDTO.setState("error");
            returnGivingGiftDTO.setExplain("??????????????????");
        }
        return returnGivingGiftDTO;
    }

    //?????????????????????
    public void saveAudioRoomChatRecord(Integer audioRoomId,String content){
        ImAudioRoomChatRecordModel imAudioRoomChatRecordModel = new ImAudioRoomChatRecordModel();
        imAudioRoomChatRecordModel.setAudioRoomId(audioRoomId);
        imAudioRoomChatRecordModel.setContent(content);
        repository.imAudioRoomChatRecordApi.save(imAudioRoomChatRecordModel);
    }

    /**                          ??????                             */


    public PsScaleOptionsModel findOptions(int scaleId){
        return repository.psScaleOptionsApi.findByScaleId(scaleId);
    }

    public Repository() {
        super();
    }

    public List<PsScaleProblemModel> findProblem(int scaleId){
        return repository.psProblemApi.findByScaleId(scaleId);
    }

    /**                          ??????                             */
    //??????????????????
    public TjUserInfoModel findUserInfo(Integer uid){
        return repository.tjUserInfoApi.findByUid(uid);
    }





    /**                          ????????????                             */

    //?????????????????????
    public ImFriendModel checkFriendShip(Integer uid, Integer firendId){
        return repository.imFriendApi.findByUidAndFriendId(uid,firendId);
    }

    //???????????????
    public void saveUserMessage(Integer uid,Integer sendId,String message){
        ImSaveUserMessageModel saveUserMessage = new ImSaveUserMessageModel();
        saveUserMessage.setUid(uid);
        saveUserMessage.setSendId(sendId);
        saveUserMessage.setMessage(message);
        repository.imSaveUserMessageApi.save(saveUserMessage);
    }

    //???????????????
    public void saveUserMessageOffOline(Integer uid,Integer sendId,String message){
        ImUserMessageModel userMessage = new ImUserMessageModel();
        userMessage.setUid(uid);
        userMessage.setSendId(sendId);
        userMessage.setMessage(message);
        userMessage.setState(1);
        repository.imUserMessageApi.save(userMessage);
    }

    public List<ImUserMessageModel> takeOffLineMessage(Integer uid){
        return repository.imUserMessageApi.uidx(uid);
    }

    public void modifyOfflineMessage(Integer uid){
        repository.imUserMessageApi.updateState(uid);
    }

    public void deleteMessage(Integer uid){
        repository.imUserMessageApi.deleteByUid(uid);
    }


    //??????????????????
    public void userChangeOnline(Integer uid){

    }

    /**                          ????????????                           */

    public TjUserInfoPojoModel userInfoModel(TjUserInfoModel tjUserInfoModel){
        return mapper.map(tjUserInfoModel,TjUserInfoPojoModel.class);
    }

}
