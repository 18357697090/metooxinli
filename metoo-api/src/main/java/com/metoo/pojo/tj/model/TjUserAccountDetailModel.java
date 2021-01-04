package com.metoo.pojo.tj.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户消费记录表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class TjUserAccountDetailModel implements Serializable {
    private Long id;

    private String content;

    private String orderNumber;

    private Integer prices;

    private String spare;

    private String type;

    private Integer uid;

    private Date creationTime;

}
