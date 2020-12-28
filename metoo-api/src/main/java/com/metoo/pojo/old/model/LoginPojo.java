package com.metoo.pojo.old.model;

import lombok.Data;

@Data
public class LoginPojo {
    private String username;
    private String password;
    private String secret;
    private String answer;
}
