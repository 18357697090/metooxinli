package com.metoo.pojo.ps.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 心理倾诉胶囊（备忘录功能）表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PsCapsuleDetailModel implements Serializable {
    private Boolean isSelf; // 是否是自己的
    private String nickName; // 昵称
    private String headImg; // 头像
    private List<String> imgList;
    private String createTimeStr; // 年-月-日 格式

    // ###################################################

    private Integer id;

    private String title;

    private String content;

    private Integer authType;

    private Integer readNum;

    private BigDecimal price;

    private Integer type;

    private Integer uid;

    private Integer state;

}
