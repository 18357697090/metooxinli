package com.metoo.pojo.old.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class GiftUser implements Serializable {
    private int uid;
    private String name;
    private int touid;
    private String toname;
    private String content;
    private Object other;
}
