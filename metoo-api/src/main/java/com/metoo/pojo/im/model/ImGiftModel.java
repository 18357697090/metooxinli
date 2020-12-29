package com.metoo.pojo.im.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 礼物表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ImGiftModel implements Serializable {

    private Long id;

    private Date creationTime;

    private Integer giftId;

    private BigDecimal prices;

    private String name;

    private String picture;
}
