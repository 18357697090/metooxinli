package com.metoo.pojo.login.model;

import com.metoo.pojo.tj.model.TjUserInfoModel;
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

    private Integer userId;
    private String extendId;
    private String username;
    private String pwd;
    private Integer state;
}
