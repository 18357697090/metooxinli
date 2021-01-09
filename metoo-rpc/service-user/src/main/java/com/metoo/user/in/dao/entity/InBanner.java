package com.metoo.user.in.dao.entity;

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
 * 
 * </p>
 *
 * @author loongya
 * @since 2021-01-07
 */
@Entity
@Table(name = "in_banner")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="InBanner对象", description="")
public class InBanner extends Model<InBanner> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "链接")
    private String link;

    @ApiModelProperty(value = "图片")
    private String img;

    @ApiModelProperty(value = "位置：1：首页顶部 2： 心理咨询页面顶部 3：心理倾诉页面顶部")
    private Integer type;

    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
