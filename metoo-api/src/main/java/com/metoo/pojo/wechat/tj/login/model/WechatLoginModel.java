package com.metoo.pojo.wechat.tj.login.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class WechatLoginModel implements Serializable {

    private String openId;
    private String username;
    private String password;
    private String assectToken;
    private String session_key;
    private Integer uid;
}
