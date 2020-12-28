package com.metoo.pojo.model.ps;

import lombok.Data;

@Data
public class RoomMessage {

    //发送数据类型   word为文字,  image为图片,  gift为刷礼物的信息, system为系统消息, user为用户进入房间,
    // out为用户离开房间, microphone 为上麦信息 , unmicrophone为下麦消息

    private String type;

    //发送者的名字
    private String uid;

    //当刷礼物的时候，某某某刷给某某某
    private Integer touid;

    //消息内容
    private Object msg;



}
