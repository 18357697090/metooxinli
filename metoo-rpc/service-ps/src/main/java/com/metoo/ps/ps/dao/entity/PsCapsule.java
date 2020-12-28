package com.metoo.ps.ps.dao.entity;

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
 * 心理倾诉胶囊（备忘录功能）表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PsCapsule对象", description="心理倾诉胶囊（备忘录功能）表")
public class PsCapsule extends Model<PsCapsule> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "1表示公开，0表示私密")
    private Integer attribute;

    @ApiModelProperty(value = "观看量")
    private Integer beWatched;

    @ApiModelProperty(value = "胶囊id")
    private Integer capsuleId;

    @ApiModelProperty(value = "胶囊内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime creationTime;

    @ApiModelProperty(value = "图片1")
    private String picture1;

    @ApiModelProperty(value = "图片2")
    private String picture2;

    private String picture3;

    private String picture4;

    private String picture5;

    private String picture6;

    private String picture7;

    private String picture8;

    private String picture9;

    @ApiModelProperty(value = "胶囊价格，0表示免费")
    private Integer prices;

    @ApiModelProperty(value = "胶囊标题")
    private String title;

    @ApiModelProperty(value = "胶囊类型")
    private Integer type;

    @ApiModelProperty(value = "用户uid")
    private Integer uid;

    @ApiModelProperty(value = "1表示正常 0表示不可见，删除")
    private Integer state;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
