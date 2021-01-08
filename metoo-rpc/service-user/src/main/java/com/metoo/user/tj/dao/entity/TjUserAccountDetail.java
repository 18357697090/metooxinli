package com.metoo.user.tj.dao.entity;

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
 * 用户消费记录表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Entity
@Table(name = "tj_user_account_detail")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TjUserAccountDetail对象", description="用户消费记录表")
public class TjUserAccountDetail extends Model<TjUserAccountDetail> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户uid")
    private Integer uid;

    @ApiModelProperty(value = "账号id")
    private Integer accountId;

    @ApiModelProperty(value = "消费内容")
    private String content;

    @ApiModelProperty(value = "变动前余额")
    private BigDecimal prePrice;

    @ApiModelProperty(value = "变动金额")
    private BigDecimal price;

    @ApiModelProperty(value = "变动后余额")
    private BigDecimal afterPrice;

    @ApiModelProperty(value = "兔币类型: 0-10000:收入 >10000:消费")
    private Integer type;

    @ApiModelProperty(value = "兔币类型文字描述")
    private Integer typeName;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
