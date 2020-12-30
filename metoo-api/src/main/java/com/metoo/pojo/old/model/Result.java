package com.metoo.pojo.old.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class Result implements Serializable {
    private Map<Integer,Integer> results;
    private int scaleId;
}
