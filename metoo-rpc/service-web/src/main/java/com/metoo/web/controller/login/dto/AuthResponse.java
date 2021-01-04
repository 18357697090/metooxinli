package com.metoo.web.controller.login.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 认证的响应结果
 *
 * @author fengshuonan
 * @Date 2017/8/24 13:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthResponse implements Serializable {

    /**
     * jwt token
     */
    private final String token;
    /**
     * 用于客户端混淆md5加密
     */
    private final String randomKey;

    public AuthResponse(String token, String randomKey) {
        this.token = token;
        this.randomKey = randomKey;
    }

}
