package com.metoo.user.ta.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "ta_task_user")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TaTaskUser对象", description="用户我的任务表")
public class TaTaskUser extends Model<TaTaskUser> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "任务id")
    private Integer taskId;

    @ApiModelProperty(value = "领去任务的用户的id")
    private Integer uid;

    @ApiModelProperty(value = "任务状态 1: 待完成 2: 待审核 3: 审核成功(金额到账) 4: 审核失败(7日后金额原路返回) 5:已关闭")
    private Integer status;

    @ApiModelProperty(value = "任务价格")
    private BigDecimal price;

    @ApiModelProperty(value = "任务领取时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
