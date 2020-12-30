package com.metoo.pojo.old.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReturnCountryDTO implements Serializable {
    private Integer countryId;
    private String name;
    private String introduction;
    private String picture;
    private String userName;
    //1表示
    private Integer statusBar=1;

}
