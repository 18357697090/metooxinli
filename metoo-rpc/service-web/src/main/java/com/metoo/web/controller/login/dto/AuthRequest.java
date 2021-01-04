package com.metoo.web.controller.login.dto;


import com.metoo.web.config.auth.validator.dto.Credence;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 认证的请求dto
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AuthRequest implements Credence {

    private String username;
    private String password;


    @Override
    public String getCredenceName() {
        return this.username;
    }

    @Override
    public String getCredenceCode() {
        return this.password;
    }
}
