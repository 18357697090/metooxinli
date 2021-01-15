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
    // 微信用户信息
    private String nickName;
    private String headImg;
    private Integer gender;
    private String province;
    private String city;
    private String openId;


    private String jsCode;
    // 1: 绑定迷途心理账号  2: 新注册
    private Integer type;
}
