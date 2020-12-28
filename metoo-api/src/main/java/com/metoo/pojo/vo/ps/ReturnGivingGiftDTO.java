package com.metoo.pojo.vo.ps;

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
