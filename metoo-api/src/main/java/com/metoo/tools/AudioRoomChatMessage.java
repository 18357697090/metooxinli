package com.metoo.tools;

import com.metoo.pojo.old.model.TjUserInfoPojoModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class AudioRoomChatMessage  implements Serializable {
    //表示消息类型 AudioRoom 表示聊天室 Friend 表示好友聊天
    private String type;
    //类型
    private String type1;
    //发送给谁
    private Integer To;
    //消息内容
    private String message;
    //附加信息
    private Object object;
    //备用
    private String spare;
    //用户信息
    private TjUserInfoPojoModel userInfo;
}
