package com.metoo.pojo.old.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoomUser  implements Serializable {
    private String type;
    private Integer uid;
    private String name;
    private String tx;
}
