package com.metoo.pojo.xy.vo;

import com.loongya.core.util.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>
 * 申请加入城消息记录表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class XyJoinCityMessageVo extends BaseVo {
    private Long id;

    private Date createTime;

    private Integer cityHostId;

    private String message;

    private Integer state;

    private Integer uid;

    private String cityName;

}
