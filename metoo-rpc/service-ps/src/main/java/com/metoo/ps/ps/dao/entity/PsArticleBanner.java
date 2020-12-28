package com.metoo.ps.ps.dao.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 心理文章轮播图表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PsArticleBanner对象", description="心理文章轮播图表")
public class PsArticleBanner extends Model<PsArticleBanner> {

    private static final long serialVersionUID = 1L;

    private Integer articleId;

    @ApiModelProperty(value = "图片")
    private String picture;

    @ApiModelProperty(value = "标题")
    private String title;


    @Override
    protected Serializable pkVal() {
        return this.articleId;
    }

}
