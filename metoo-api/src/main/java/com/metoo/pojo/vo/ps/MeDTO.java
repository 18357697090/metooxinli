package com.metoo.pojo.vo.ps;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MeDTO {
    private BigDecimal balance;
    private Integer activeIntegral;
    private Integer psychologyIntegral;
    private String picture;
    private String name;
    private String motto;
    private Integer level;

}
