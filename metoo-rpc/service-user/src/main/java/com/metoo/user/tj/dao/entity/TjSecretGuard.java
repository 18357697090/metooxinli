package com.metoo.user.tj.dao.entity;

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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * <p>
 * 用户密保问题
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Entity
@Table(name = "tj_secret_guard")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TjSecretGuard对象", description="用户密保问题")
public class TjSecretGuard extends Model<TjSecretGuard> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户uid")
    private Integer uid;

    @ApiModelProperty(value = "登录账号")
    private String username;

    @ApiModelProperty(value = "密保答案")
    private String answer;

    @ApiModelProperty(value = "密保问题")
    private String secretGuard;

    @ApiModelProperty(value = "创建时间")
    private Date creationTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
