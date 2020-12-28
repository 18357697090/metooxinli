package com.metoo.pojo.old.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PourOutCapsulePojo {
    private Integer capsuleId;
    private String creationTime;
    private String title;
    private Integer beWatched;
    private BigDecimal prices;
    private Integer uid;
    private String picture;
    private String name;
    private Integer attribute;
}
