package com.metoo.ps.ps.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;

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
 * 心理倾诉胶囊（备忘录功能）表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PsCapsule对象", description="心理倾诉胶囊（备忘录功能）表")
public class PsCapsule extends Model<PsCapsule> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "权限：1表示公开，0表示私密")
    private Integer authType;

    @ApiModelProperty(value = "观看量")
    private Integer readNum;

    @ApiModelProperty(value = "胶囊标题")
    private String title;

    @ApiModelProperty(value = "胶囊内容")
    private String content;

    @ApiModelProperty(value = "胶囊价格，0表示免费")
    private BigDecimal price;

    @ApiModelProperty(value = "胶囊类型(1:爱情 2：友情 3：癖好 4：心事)")
    private Integer type;

    @ApiModelProperty(value = "用户uid")
    private Integer uid;

    @ApiModelProperty(value = "状态：0：正常 1：屏蔽 2： 删除")
    private Integer state;


    @CreatedDate
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
