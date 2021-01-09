package com.metoo.ps.ps.dao.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 倾诉师订单
 * </p>
 *
 * @author loongya
 * @since 2021-01-07
 */
@Entity
@Table(name = "ps_pour_out_order")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PsPourOutOrder对象", description="倾诉师订单")
public class PsPourOutOrder extends Model<PsPourOutOrder> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "倾诉师表id")
    private Integer pourId;

    @ApiModelProperty(value = "咨询用户id")
    private Integer userId;

    @ApiModelProperty(value = "状态：1：进行中， 2：已结束")
    private Integer status;

    @ApiModelProperty(value = "倾诉价格")
    private BigDecimal price;

    private Date createTime;

    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
