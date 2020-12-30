package com.metoo.pojo.old.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class testPojo implements Serializable {
    private List<String> strings;
}
