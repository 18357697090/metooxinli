package com.metoo.pojo.ta.vo;

import com.loongya.core.util.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

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
public class MyTaTaskVo extends BaseVo {
    // 用户id
    private Integer uid;
    // 任务id
    private Integer taskId;
    //我接受的 任务状态 1: 待完成 2: 待审核 3: 审核成功(金额到账) 4: 审核失败(7日后金额原路返回) 5:已关闭
    // 我发布的任务状态 任务状态 1正常 2: 异常 3:删除
    private Integer status;
    // 普通任务, 教程任务
    private Integer type;
    // 开始时间
    private String startTime;
    // 结束时间
    private String endTime;
}
