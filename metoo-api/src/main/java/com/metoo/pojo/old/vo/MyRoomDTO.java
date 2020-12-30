package com.metoo.pojo.old.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyRoomDTO implements Serializable {
    private Integer RoomId;
    private String name;
    private String picture;
    private String introduction;
}
