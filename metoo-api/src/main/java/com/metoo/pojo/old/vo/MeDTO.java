package com.metoo.pojo.old.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MeDTO implements Serializable {
    private BigDecimal balance;
    private BigDecimal activeIntegral;
    private BigDecimal psychologyIntegral;
    private String picture;
    private String name;
    private String motto;
    private Integer level;

}
