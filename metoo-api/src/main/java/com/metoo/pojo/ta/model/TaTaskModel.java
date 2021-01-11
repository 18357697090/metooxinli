package com.metoo.pojo.ta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.ArrayList;
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
public class TaTaskModel implements Serializable {

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
    // 是否可以领取 1: 已经领取 0:可以领取
    private Integer getStatus;
    // 图片列表
    private List<TaTaskImgModel> imgModelList;
    // 我对该任务的状态 任务状态 1: 待完成 2: 待审核 3: 审核成功(金额到账) 4: 审核失败(7日后金额原路返回) 5:已关闭
    private Integer taskUserStatus;
    // 我对该任务的状态 任务状态 1: 待完成 2: 待审核 3: 审核成功(金额到账) 4: 审核失败(7日后金额原路返回) 5:已关闭
    private String taskUserStatusName;


    private String appealRemark;

    private Integer appealStatus;

    private String appealAnswer;
    // 待完成列表
    List<TaTaskUserModel> goOnList = new ArrayList<>();
    // 待确认列表
    List<TaTaskUserModel> waitConfirmList = new ArrayList<>();
    // 已结束列表
    List<TaTaskUserModel> finishList = new ArrayList<>();
    // 我领取任务的记录表id
    private Integer taskUserId;

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
