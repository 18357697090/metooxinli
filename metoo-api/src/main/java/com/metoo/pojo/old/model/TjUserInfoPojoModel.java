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
public class TjUserInfoPojoModel implements Serializable {
    private Long id;

    private Integer age;

    private String city;

    private Integer dw;

    private Integer gender;

    private String name;

    private Integer uid;

    private String picture;

    private String motto;

}
