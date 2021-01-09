package com.metoo.pojo.ta.vo;

import com.loongya.core.util.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
public class TaTaskVo extends BaseVo {
    //任务id
    private Integer taskId;
    //任务标题
    private String title;
    //任务内容
    private String content;
    //任务指定用户id(",")
    private String userIds;
    //任务金额
    private BigDecimal price;
    //任务段位
    private String levels;
    //任务类型 1表示普通任务，2表示教程任务类型
    private Integer type;
    // 任务图片 (",")
    private String imgs;
    // 用户id
    private Integer uid;
    // 任务开始时间
    private Date startTime;
    // 任务结束时间
    private Date endTime;
    // 限制领取人数 限制领取任务人数(1:只能一人 ,2:多人)
    private Integer personNum;

}
