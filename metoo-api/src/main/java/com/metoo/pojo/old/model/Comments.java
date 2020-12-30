package com.metoo.pojo.old.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Comments implements Serializable {
    private String userPicture;
    private String username;
    private String comment;
    private String levelPicture;
    private Date createTime;
}
