package com.metoo.pojo.old.vo;

import lombok.Data;

@Data
public class ReturnCountryDTO {
    private Integer countryId;
    private String name;
    private String introduction;
    private String picture;
    private String userName;
    //1表示
    private Integer statusBar=1;

}