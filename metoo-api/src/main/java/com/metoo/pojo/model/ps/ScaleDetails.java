package com.metoo.pojo.model.ps;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ScaleDetails {

    private String topPicture;
    private String title;
    private BigDecimal price;
    private String content;
    private int numberOfPeople;
    private int numberOfProblem;
    private String contentPicture;
    private List<com.metoo.metoo.pojo.Comments> comments;
    private String scaleNeedToKnow;
    private String state;
    private BigDecimal accountBalance;

}
