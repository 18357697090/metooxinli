package com.metoo.user.tj.dao.entity;

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
 * 积分使用详情类
 * </p>
 *
 * @author loongya
 * @since 2021-01-08
 */
@Entity
@Table(name = "tj_user_account_point_detail")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TjUserAccountPointDetail对象", description="积分使用详情类")
public class TjUserAccountPointDetail extends Model<TjUserAccountPointDetail> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer uid;

    @ApiModelProperty(value = "用户账户id")
    private Integer accountId;

    @ApiModelProperty(value = "积分类型:1:活动积分 2:心理积分")
    private Integer pointType;

    @ApiModelProperty(value = "变动前积分")
    private BigDecimal prePoint;

    @ApiModelProperty(value = "变动积分")
    private BigDecimal point;

    @ApiModelProperty(value = "变动后积分")
    private BigDecimal afterPoint;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "变动类型: 1-10000: 收入  >10000 支出")
    private Integer type;

    @ApiModelProperty(value = "变动类型:名称")
    private String typeName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
