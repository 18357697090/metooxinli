package com.metoo.pojo.old.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BuildCityDTO implements Serializable {
    private Integer countryId;
    // 1 为正常的城，2，商家馆  3，倾诉馆 4，请安馆 5，匹配
    private Integer type;
    private String name;
    private String picture;
    private String introduction;
}
