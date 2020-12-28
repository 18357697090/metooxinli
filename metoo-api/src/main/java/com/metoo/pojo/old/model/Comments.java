package com.metoo.pojo.old.model;

import lombok.Data;

import java.util.Date;

@Data
public class Comments {
    private String userPicture;
    private String username;
    private String comment;
    private String levelPicture;
    private Date createTime;
}
