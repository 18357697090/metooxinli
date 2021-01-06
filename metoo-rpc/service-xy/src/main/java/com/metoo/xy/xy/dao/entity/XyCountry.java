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
 * 国度表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Entity
@Table(name = "xy_country")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="XyCountry对象", description="国度表")
public class XyCountry extends Model<XyCountry> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @CreatedDate
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "国id")
    private Integer countryId;

    @ApiModelProperty(value = "介绍")
    private String introduction;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "图片")
    private String picture;

    @ApiModelProperty(value = "族id")
    private Integer raceId;

    @ApiModelProperty(value = "备用字段")
    private String spare;

    @ApiModelProperty(value = "1正常。0被删除")
    private Integer state;

    @ApiModelProperty(value = "是否为聊天室")
    private Integer isAudioRoom;

    @ApiModelProperty(value = "是否为城")
    private Integer isCity;

    @ApiModelProperty(value = "是否为国")
    private Integer isCountry;

    @ApiModelProperty(value = "是否为族")
    private Integer isRace;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
