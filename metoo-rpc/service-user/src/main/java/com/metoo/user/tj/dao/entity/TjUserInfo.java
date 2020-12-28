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

/**
 * <p>
 * 用户个人信息表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TjUserInfo对象", description="用户个人信息表")
public class TjUserInfo extends Model<TjUserInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "段位")
    private Integer dw;

    @ApiModelProperty(value = "用户性别，1表示男，2表示女")
    private Integer gender;

    @ApiModelProperty(value = "昵称")
    private String name;

    @ApiModelProperty(value = "uid")
    private Integer uid;

    @ApiModelProperty(value = "头像")
    private String picture;

    @ApiModelProperty(value = "签名")
    private String motto;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime creationTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
