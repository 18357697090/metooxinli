package com.metoo.im.im.dao.entity;

import java.math.BigDecimal;
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
 * 礼物表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Entity
@Table(name = "im_gift")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ImGift对象", description="礼物表")
public class ImGift extends Model<ImGift> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @CreatedDate
    private Date createTime;

    @ApiModelProperty(value = "礼物id")
    private Integer giftId;

    @ApiModelProperty(value = "礼物价格")
    private BigDecimal prices;

    @ApiModelProperty(value = "礼物名称")
    private String name;

    @ApiModelProperty(value = "礼物图片")
    private String picture;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
