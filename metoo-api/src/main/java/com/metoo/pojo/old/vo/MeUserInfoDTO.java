package com.metoo.pojo.old.vo;

import lombok.Data;

import java.io.Serializable;


@Data
public class MeUserInfoDTO implements Serializable {

    private Integer uid;
    private String name;
    private String picture;
    private Integer age;
    private Integer gender;
    private Integer dw;
    private String city;
    private String motto="用户很懒，没用设置签名";
    private Integer state=0;
}
