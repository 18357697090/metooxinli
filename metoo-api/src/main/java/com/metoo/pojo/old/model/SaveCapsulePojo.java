package com.metoo.pojo.old.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SaveCapsulePojo {
    private List<String> picture;
    private String title;
    private String content;
    private Integer attribute;
    private BigDecimal prices;
}
