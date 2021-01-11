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
public class AppealTaskVo extends BaseVo {
    // 用户id
    private Integer uid;
    // 任务id
    private Integer taTaskUserId;
    // 申诉内容
    private String appealRemark;

}
