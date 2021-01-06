package com.metoo.user.in.dao.entity;

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
 * 首页轮播图
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@Entity
@Table(name = "in_swiper")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="InSwiper对象", description="首页轮播图")
public class InSwiper extends Model<InSwiper> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "点击跳转地址")
    private String address;

    @ApiModelProperty(value = "首页轮播图图片")
    private String swiper;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
