package com.metoo.pojo.login.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo implements Serializable {

    private String username;
    private String password;
    private String repeatPassword;
    private String secret;
    private String answer;

    private String jsCode;
}
