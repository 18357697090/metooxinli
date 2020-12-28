package com.metoo.pojo.old.vo;

import com.metoo.metoo.entity.Gift;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReturnGivingGiftDTO {
    private BigDecimal balance;
    private String state;
    private String explain;
    private Gift gift;
}
