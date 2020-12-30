package com.metoo.pojo.old.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReturnMyCityList  implements Serializable {
    private Integer myCityId;
    private String name;
}
