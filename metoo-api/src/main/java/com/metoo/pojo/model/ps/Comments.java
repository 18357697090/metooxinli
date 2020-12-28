package com.metoo.pojo.model.ps;

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
