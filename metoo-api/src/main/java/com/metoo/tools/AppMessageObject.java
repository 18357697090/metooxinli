package com.metoo.tools;

import lombok.Data;

import java.io.Serializable;

@Data
public class AppMessageObject implements Serializable {
    private Integer uid;
    private String isSuccess;
}
