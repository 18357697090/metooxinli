package com.metoo.pojo.old.vo;

import com.metoo.pojo.im.model.ImGiftModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ReturnGivingGiftDTO  implements Serializable {
    private BigDecimal balance;
    private String state;
    private String explain;
    private ImGiftModel gift;
}
