package com.metoo.pojo.vo.ps;

import com.metoo.metoo.entity.UserInfo;
import lombok.Data;

import java.util.List;

@Data
public class JoinAudioRoomDTO {
    private List<UserInfo> userInfos;
    private UserInfo hostUserInfo;
    private String content;

}
