package com.metoo.pojo.old.vo;

import com.metoo.pojo.old.model.TjUserInfoPojoModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DetailAudioRoomDTO implements Serializable {
    private List<TjUserInfoPojoModel> userInfos;
    // 1是房主权限，0不是
    private Integer state;

}
