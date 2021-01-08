package com.metoo.pojo.nr.vo;

import com.loongya.core.util.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class NrGoodsVo extends BaseVo {
    private Integer userId;
    private Integer goodsId;
    private Integer extendId;
}
