package com.metoo.user.ta.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * <p>
 * 用户我的任务表
 * </p>
 *
 * @author loongya
 * @since 2020-12-29
 */
@Entity
@Table(name = "ta_user_task")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TaUserTask对象", description="用户我的任务表")
public class TaUserTask extends Model<TaUserTask> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "接受任务的用户的id")
    private Integer acceptId;

    @ApiModelProperty(value = "接受人的状态，1表示确认完成  0表示未确认")
    private Integer acceptState;

    @ApiModelProperty(value = "发布人uid")
    private Integer publishId;

    @ApiModelProperty(value = "发布人的状态，1表示确认完成  0表示未确认")
    private Integer publishState;

    @ApiModelProperty(value = "1表示发布中，2表示进行中，3表示已完成")
    private Integer state;

    @ApiModelProperty(value = "任务id")
    private Integer taskId;

    @ApiModelProperty(value = "1表示普通任务，2表示教程任务")
    private Integer type;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
