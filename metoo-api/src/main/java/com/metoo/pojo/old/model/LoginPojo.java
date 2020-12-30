package com.metoo.pojo.old.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginPojo implements Serializable {
    private String username;
    private String password;
    private String secret;
    private String answer;
}
