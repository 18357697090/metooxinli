package com.metoo.pojo.login.model;

import com.metoo.pojo.tj.model.TjUserInfoModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserInfoModel implements Serializable {

    private Integer state;

    private Integer userId;

    private String username;

    private Integer phoneNumber;

    private String spare;

    private TjUserInfoModel tjUserInfoModel;

}
