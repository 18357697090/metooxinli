package com.metoo.pojo.model.ps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TjUserInfoModel implements Serializable {
    private String name;
    private String picture;
    private Integer age;
    private Integer gender;
    private String city;
}
