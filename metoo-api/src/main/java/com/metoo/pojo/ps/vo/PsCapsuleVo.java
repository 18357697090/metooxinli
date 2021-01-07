package com.metoo.pojo.ps.vo;

import com.loongya.core.util.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
public class PsCapsuleVo extends BaseVo {

    private Integer userId;
    private String imgs; // 图片 “，”隔开
    private String title;
    private String content;
    private Integer authType; // 权限：1表示公开，0表示私密
    private BigDecimal price; // 胶囊价格，0表示免费

    private Integer capsuleId;

}
