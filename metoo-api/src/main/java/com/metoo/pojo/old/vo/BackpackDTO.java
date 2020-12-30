package com.metoo.pojo.old.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BackpackDTO implements Serializable {
    private Integer type;
    private Integer number;
    private String content;
    private String name;
    private String picture;
}
