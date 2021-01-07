package com.metoo.pojo.ps.model;

import com.metoo.pojo.old.model.Comments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 心理测量量表详情表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PsScaleDetailModel implements Serializable{
    private String topPicture;
    private String title;
    private BigDecimal price;
    private String content;
    private Integer numberOfPeople;
    private Integer numberOfProblem;
    private Integer commentCount; // 评价总数
    private String contentPicture;
    private List<PsScaleCommentModel> comments;
    private String scaleNeedToKnow;
    private String state;
    private BigDecimal accountBalance;
}
