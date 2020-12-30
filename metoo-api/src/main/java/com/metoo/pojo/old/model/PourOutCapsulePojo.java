package com.metoo.pojo.old.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class PourOutCapsulePojo implements Serializable {
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
