package com.metoo.pojo.old.vo;

import com.metoo.pojo.old.model.TjUserInfoPojoModel;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import lombok.Data;

import java.util.List;

@Data
public class JoinAudioRoomDTO {
    private List<TjUserInfoModel> userInfos;
    private TjUserInfoModel hostUserInfo;
    private String content;

}
