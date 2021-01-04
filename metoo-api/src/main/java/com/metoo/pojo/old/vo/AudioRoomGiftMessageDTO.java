package com.metoo.pojo.old.vo;

import com.metoo.pojo.im.model.ImGiftModel;
import com.metoo.pojo.old.model.TjUserInfoPojoModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AudioRoomGiftMessageDTO implements Serializable {
    private ReturnGivingGiftDTO returnGivingGiftDTO;
    private List<TjUserInfoPojoModel> userInfos;
    private ImGiftModel gift;
    private String number;
}
