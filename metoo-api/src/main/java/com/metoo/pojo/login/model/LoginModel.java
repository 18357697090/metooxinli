package com.metoo.pojo.login.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class LoginModel implements Serializable {

    private String userId;
    private String username;
    private String pwd;
    private Integer state;
}
