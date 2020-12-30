package com.metoo.pojo.old.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SecretGuardPojo implements Serializable {
    private String username;
    private String answer;
    private String newPassword;
}
