package com.metoo.tools;

import lombok.Data;

import java.io.Serializable;

@Data
public class zc implements Serializable {
    private String state="error";
    private Integer uid;
    private String name;
    private String picture;
}
