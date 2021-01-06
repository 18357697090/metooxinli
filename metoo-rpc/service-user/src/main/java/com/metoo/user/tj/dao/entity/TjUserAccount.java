package com.metoo.user.tj.dao.entity;

import java.math.BigDecimal;
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
 * 用户账户表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Entity
@Table(name = "tj_user_account")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TjUserAccount对象", description="用户账户表")
public class TjUserAccount extends Model<TjUserAccount> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户uid")
    private Integer uid;

    @ApiModelProperty(value = "活动积分")
    private BigDecimal activeIntegral;

    @ApiModelProperty(value = "余额")
    private BigDecimal balance;

    @ApiModelProperty(value = "心理积分")
    private BigDecimal psychologyIntegral;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
