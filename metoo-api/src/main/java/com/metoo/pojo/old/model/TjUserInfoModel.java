package com.metoo.pojo.old.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class TjUserInfoModel implements Serializable {
    private String name;
    private String picture;
    private Integer age;
    private Integer gender;
    private String city;
}
