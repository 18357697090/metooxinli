package com.metoo.pojo.ta.vo;

import com.loongya.core.util.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 任务大厅表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CommitTaTaskVo extends BaseVo {
    // 用户id
    private Integer uid;
    // 用户领取任务表id
    private Integer taskUserId;

    private String remark;

    private String imgs;

}