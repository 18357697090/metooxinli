package com.metoo.order.im.dao.entity;

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
 * 礼物记录表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Entity
@Table(name = "im_gift_record")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ImGiftRecord对象", description="礼物记录表")
public class ImGiftRecord extends Model<ImGiftRecord> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "送礼物的人id")
    private Integer uid;

    @CreatedDate
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "接受礼物的用户id")
    private Integer accepted;

    @ApiModelProperty(value = "礼物id")
    private Integer giftId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
