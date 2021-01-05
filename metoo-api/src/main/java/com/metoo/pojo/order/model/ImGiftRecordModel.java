package com.metoo.pojo.order.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 礼物记录表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ImGiftRecordModel implements Serializable {

    private Long id;

    private Integer uid;

    private Date createTime;

    private Integer accepted;

    private Integer giftId;

}
