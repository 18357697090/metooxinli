package com.metoo.pojo.ps.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 心理倾诉师表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PsPourOutModel implements Serializable {
    private Integer status; // 服务状态： 1：正常状态  2：进行服务中 3：有未完成的倾诉，可以继续倾诉

    private Integer id;

    private String label;

    private String level;

    private String name;

    private Integer uid;

    private String content;

    private BigDecimal price;

    private String headImg;

    private Integer sort;

    private String spare;

    private Integer onLine;
}
