package com.metoo.pojo.login.vo;

import com.loongya.core.util.vo.BaseVo;
import com.metoo.pojo.tj.model.TjUserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class LoginUploadPasswordVo extends BaseVo {

    private String username;

    private String question;

    private String answer;

    private String password;

    private String repeatPassword;
}
