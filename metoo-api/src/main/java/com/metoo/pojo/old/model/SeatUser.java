package com.metoo.pojo.old.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SeatUser implements Serializable {
    private int uid;
    private String name;
    private String tx;
}
