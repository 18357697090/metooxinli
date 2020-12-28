package com.metoo.ps.ps.dao.entity;

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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * <p>
 * 用户心理测量记录表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PsUserAndMeasure对象", description="用户心理测量记录表")
public class PsUserAndMeasure extends Model<PsUserAndMeasure> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "购买过几次")
    private Integer count;

    @ApiModelProperty(value = "量表id")
    private Integer scaleId;

    @ApiModelProperty(value = "0表示没购买    1表示购买未完成测试  2表示已经测试过")
    private Integer state;

    @ApiModelProperty(value = "用户id")
    private Integer uid;

    @CreatedDate
    @ApiModelProperty(value = "创建时间")
    private Date creationTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
