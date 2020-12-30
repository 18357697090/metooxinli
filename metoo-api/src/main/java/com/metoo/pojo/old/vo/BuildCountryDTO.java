package com.metoo.pojo.old.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BuildCountryDTO implements Serializable {
    private Integer raceId;
    private String name;
    private String picture;
    private String introduction;
}
