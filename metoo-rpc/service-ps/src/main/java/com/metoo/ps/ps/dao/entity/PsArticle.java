package com.metoo.ps.ps.dao.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 心理文章表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PsArticle对象", description="心理文章表")
public class PsArticle extends Model<PsArticle> {

    private static final long serialVersionUID = 1L;

    private Integer articleId;

    @ApiModelProperty(value = "文章内容：富文本格式")
    private String content;

    @ApiModelProperty(value = "文章图片")
    private String picture;

    @ApiModelProperty(value = "1为正常展示，0为不展示")
    private Integer state;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章排序类型")
    private Integer sort;

    @ApiModelProperty(value = "查看文章的人数")
    private Integer number;


    @Override
    protected Serializable pkVal() {
        return this.articleId;
    }

}
