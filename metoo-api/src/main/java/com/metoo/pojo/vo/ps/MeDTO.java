package com.metoo.pojo.vo.ps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeDTO implements Serializable {
    private BigDecimal balance;
    private Integer activeIntegral;
    private Integer psychologyIntegral;
    private String picture;
    private String name;
    private String motto;
    private Integer level;

}
