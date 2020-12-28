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
 * 我的国度表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Entity
@Table(name = "xy_my_country")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="XyMyCountry对象", description="我的国度表")
public class XyMyCountry extends Model<XyMyCountry> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @CreatedDate
    @ApiModelProperty(value = "创建时间")
    private Date creationTime;

    @ApiModelProperty(value = "公告")
    private String notice;

    @ApiModelProperty(value = "国家id")
    private Integer countryId;

    @ApiModelProperty(value = "是否为房主")
    private Integer isHost;

    @ApiModelProperty(value = "0表示退出或者被T  1表示正常")
    private Integer state;

    @ApiModelProperty(value = "用户uid")
    private Integer uid;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
