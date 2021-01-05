package com.metoo.xy.xy.dao.entity;

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
 * 申请加入城消息记录表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Entity
@Table(name = "xy_join_city_message")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="XyJoinCityMessage对象", description="申请加入城消息记录表")
public class XyJoinCityMessage extends Model<XyJoinCityMessage> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @CreatedDate
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "群主id")
    private Integer cityHostId;

    @ApiModelProperty(value = "申请内容")
    private String message;

    @ApiModelProperty(value = "1表示未处理，0表示处理了")
    private Integer state;

    @ApiModelProperty(value = "申请用户uid")
    private Integer uid;

    @ApiModelProperty(value = "城名称")
    private String cityName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
