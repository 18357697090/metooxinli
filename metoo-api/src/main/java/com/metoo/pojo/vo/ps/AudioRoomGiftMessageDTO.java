package com.metoo.pojo.vo.ps;

import com.metoo.metoo.entity.Gift;
import com.metoo.metoo.entity.UserInfo;
import lombok.Data;

import java.util.List;

@Data
public class AudioRoomGiftMessageDTO {
    private com.metoo.metoo.DTO.ReturnGivingGiftDTO returnGivingGiftDTO;
    private List<UserInfo> userInfos;
    private Gift gift;
    private String number;
}
