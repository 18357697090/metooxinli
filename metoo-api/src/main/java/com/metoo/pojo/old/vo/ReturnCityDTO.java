package com.metoo.pojo.old.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReturnCityDTO implements Serializable {
    private Integer cityId;
    private Integer type;
    private String name;
    private String picture;
    private String introduction;
    private String username;
}
