package com.metoo.pojo.order.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户购买胶囊记录表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PsUserBuyCapsuleModel implements Serializable {
    private Long id;

    private Integer capsuleId;

    private Integer uid;

    private Date createTime;

}
