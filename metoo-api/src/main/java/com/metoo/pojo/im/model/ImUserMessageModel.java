package com.metoo.pojo.im.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户聊天记录表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ImUserMessageModel implements Serializable{
    private long id;
    private Date createTime;
    private Integer uid;
    private Integer sendId;
    private String message;
    //0表示已读，1表示未读
    private Integer state;
    private String spare;


}
