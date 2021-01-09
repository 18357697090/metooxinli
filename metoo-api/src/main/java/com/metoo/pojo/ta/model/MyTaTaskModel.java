package com.metoo.pojo.ta.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 任务大厅表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MyTaTaskModel implements Serializable {

    // 发布人头像
    private String headImg;
    // 人不如昵称
    private String nickName;
    // 性别
    private Integer sex;
    // 年龄
    private Integer age;
    // 城市
    private String city;
    // 图片列表
    private List<TaTaskImgModel> imgModelList;

    private Integer id;

    private Integer uid;

    private String title;

    private String content;

    private BigDecimal price;

    private Integer state;

    private Integer type;

    private String remark;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private Date updateTime;

}
