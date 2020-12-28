package com.metoo.pojo.model.ps;

import lombok.Data;

@Data
public class GiftUser {
    private int uid;
    private String name;
    private int touid;
    private String toname;
    private String content;
    private Object other;
}
