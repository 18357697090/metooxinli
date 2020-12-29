package com.metoo.user.tj.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Entity
@Table(name = "tj_user")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TjUser对象", description="用户表")
public class TjUser extends Model<TjUser> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户账号状态，1表示正常，0表示被封")
    private Integer state;

    @ApiModelProperty(value = "全局唯一的uid")
    private Integer uid;

    @ApiModelProperty(value = "登录账号")
    private String username;

    @ApiModelProperty(value = "手机号")
    private Integer phoneNumber;

    @ApiModelProperty(value = "备用字段")
    private String spare;

    @ApiModelProperty(value = "创建时间")
    private Date creationTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
