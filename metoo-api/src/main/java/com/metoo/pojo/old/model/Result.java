package com.metoo.pojo.old.model;

import lombok.Data;

import java.util.Map;

@Data
public class Result{
    private Map<Integer,Integer> results;
    private int scaleId;
}
