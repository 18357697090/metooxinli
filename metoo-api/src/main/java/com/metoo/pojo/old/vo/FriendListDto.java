package com.metoo.pojo.old.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class FriendListDto implements Serializable {
    private Integer uid;
    private String name;
    private String picture;
    private String motto;
    private Integer gender;
    private String city;
    private Integer age;
    private Integer dw;
    private Integer state;
}
