package com.metoo.ps.ps.dao.entity;

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
 * 心理测量量表题目表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PsProblem对象", description="心理测量量表题目表")
public class PsProblem extends Model<PsProblem> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "题目因子类型")
    private Integer factorType;

    @ApiModelProperty(value = "题目id")
    private Integer pid;

    @ApiModelProperty(value = "题目问题")
    private String problem;

    @ApiModelProperty(value = "量表id")
    private Integer scaleId;

    @ApiModelProperty(value = "题目类型")
    private Integer type;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
