package com.metoo.web.controller.backup;

import com.metoo.metoo.DTO.MeDTO;
import com.metoo.metoo.DTO.MeUserInfoDTO;
import com.metoo.metoo.DTO.MeasureRecordDTO;
import com.metoo.metoo.DTO.ModifyUserIfoDTO;
import com.metoo.metoo.entity.Friend;
import com.metoo.metoo.entity.UserInfo;
import com.metoo.metoo.entity.Zh;
import com.metoo.metoo.psychology.MeasureRecord;
import com.metoo.metoo.psychologyDao.MeasureRecordDao;
import com.metoo.metoo.repository.FriendDao;
import com.metoo.metoo.repository.UserInfoDao;
import com.metoo.metoo.repository.ZhDao;
import com.metoo.metoo.tools.ReturnMessage;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/me")
public class MeHandler {
    @Autowired
    private ZhDao zhDao;
    @Autowired
    private MeasureRecordDao measureRecordDao;
    @Autowired
    private Mapper mapper;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private FriendDao friendDao;


    @GetMapping("/me")
    public MeDTO me(@RequestHeader("UID")Integer uid){
        System.out.println(uid);
        Zh zh = zhDao.findByUid(uid);
        UserInfo userInfo = userInfoDao.findByUid(uid);
        MeDTO meDTO = new MeDTO();
        meDTO.setMotto(userInfo.getMotto());
        meDTO.setPicture(userInfo.getPicture());
        meDTO.setLevel(userInfo.getDw());
        meDTO.setName(userInfo.getName());
        meDTO.setActiveIntegral(zh.getActiveIntegral());
        meDTO.setPsychologyIntegral(zh.getPsychologyIntegral());
        meDTO.setBalance(zh.getBalance());
        return meDTO;
    }

    @GetMapping("/measureRecord")
    public List<MeasureRecordDTO> measureRecord(@RequestHeader("UID")Integer uid, String time){
        List<MeasureRecord> measureRecords = measureRecordDao.findBytime(time,uid);
        List<MeasureRecordDTO> measureRecordDTOS = new ArrayList<>();
        for (MeasureRecord measureRecord : measureRecords){
            MeasureRecordDTO measureRecordDTO = mapper.map(measureRecord,MeasureRecordDTO.class);
            measureRecordDTOS.add(measureRecordDTO);
        }
        return measureRecordDTOS;
    }

    @GetMapping("/userInfo")
    public MeUserInfoDTO userInfo(@RequestHeader("UID")Integer uid1, Integer uid){
        MeUserInfoDTO meUserInfoDTO = mapper.map(userInfoDao.findByUid(uid),MeUserInfoDTO.class);
        Friend friend = friendDao.findByUidAndFriendId(uid1,uid);
        if (friend!=null){
            meUserInfoDTO.setState(friend.getState());
        }
        return meUserInfoDTO;
    }

    //修改个人信息
    @PostMapping("/modifyUserInfo")
    public ReturnMessage modifyUserInfo(@RequestBody ModifyUserIfoDTO modifyUserIfoDTO,@RequestHeader("UID")Integer uid){
        ReturnMessage returnMessage = new ReturnMessage();
        int i = userInfoDao.updateUserInfo(modifyUserIfoDTO.getName(),modifyUserIfoDTO.getPicture(),modifyUserIfoDTO.getCity(),modifyUserIfoDTO.getMotto(),uid);
        if(i==1){
            returnMessage.setState("success");
            returnMessage.setExplain("修改成功");
        }else {
            returnMessage.setState("error");
            returnMessage.setExplain("修改失败");
        }
        return returnMessage;
    }

}
