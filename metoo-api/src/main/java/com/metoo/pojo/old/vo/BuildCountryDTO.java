package com.metoo.pojo.old.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BuildCountryDTO implements Serializable {
    private String name;
    private String img;
    private String detail;
}
