package com.metoo.pojo.model.ps;

import lombok.Data;

import java.util.Map;

@Data
public class Result{
    private Map<Integer,Integer> results;
    private int scaleId;
}
