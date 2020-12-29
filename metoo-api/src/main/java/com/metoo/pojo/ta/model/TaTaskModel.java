package com.metoo.pojo.ta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class TaTaskModel implements Serializable {
    private Long id;

    private String spare;

    private Integer state;

    private Integer taskAppoint;

    private String taskContent;

    private Integer taskId;

    private Integer taskLevel;

    private Integer taskPrices;

    private String taskTitle;

    private Integer uid;

    private Integer type;

    private Date creationTime;

    private String picture;

}
