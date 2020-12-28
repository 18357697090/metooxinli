package com.metoo.pojo.vo.ps;

import com.metoo.metoo.entity.UserInfo;
import lombok.Data;

import java.util.List;

@Data
public class DetailAudioRoomDTO {
    private List<UserInfo> userInfos;
    // 1是房主权限，0不是
    private Integer state;

}
