package com.metoo.pojo.old.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ModifyUserIfoDTO implements Serializable {
    private String name;
    private String picture;
    private String city;
    private String motto;
}
