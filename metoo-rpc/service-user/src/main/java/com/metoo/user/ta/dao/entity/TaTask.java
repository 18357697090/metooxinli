package com.metoo.user.ta.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * <p>
 * 任务大厅表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Entity
@Table(name = "ta_task")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TaTask对象", description="任务大厅表")
public class TaTask extends Model<TaTask> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "备用字段")
    private String spare;

    @ApiModelProperty(value = "任务状态 1表示发布中 2表示被接受,3表示删除")
    private Integer state;

    @ApiModelProperty(value = "任务指定的用户id")
    private Integer taskAppoint;

    @ApiModelProperty(value = "任务内容")
    private String taskContent;

    @ApiModelProperty(value = "任务id")
    private Integer taskId;

    @ApiModelProperty(value = "任务指定等级")
    private Integer taskLevel;

    @ApiModelProperty(value = "任务价格")
    private Integer taskPrices;

    @ApiModelProperty(value = "任务标题")
    private String taskTitle;

    @ApiModelProperty(value = "发布任务的用户uid")
    private Integer uid;

    @ApiModelProperty(value = "1表示普通任务，2表示教程任务类型")
    private Integer type;

    @ApiModelProperty(value = "创建时间")
    private Date creationTime;

    @ApiModelProperty(value = "图片")
    private String picture;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
