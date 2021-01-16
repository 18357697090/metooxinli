package com.metoo.pojo.ps.model;

import com.metoo.pojo.tj.model.TjUserInfoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户心理测量量表记录表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PsScaleMeasureRecordModel implements Serializable {

    private TjUserInfoModel tjUserInfoModel;
    private PsScaleModel psScaleModel;
    private Integer scaleId;
    private Integer score;
    private String content;
    private String scaleName;
    private Date createTime;
    private Integer id;
    private Integer uid;

    private Integer count;

    private Integer state;
}
