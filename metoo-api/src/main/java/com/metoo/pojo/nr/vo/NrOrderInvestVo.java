package com.metoo.pojo.nr.vo;

import com.loongya.core.util.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class NrOrderInvestVo extends BaseVo {
    private Integer uid;
    private BigDecimal price;
    private Integer orderNo;
}
