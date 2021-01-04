package com.metoo.pojo.login.model;

import com.metoo.pojo.tj.model.TjUserInfoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserInfoModel implements Serializable {

    private Integer state;

    private Integer userId;

    private String username;

    private String phone;

    private TjUserInfoModel tjUserInfoModel;

    private List<String> list;

}
