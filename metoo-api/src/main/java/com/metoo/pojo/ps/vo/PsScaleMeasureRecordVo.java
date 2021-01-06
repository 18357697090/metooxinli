package com.metoo.pojo.ps.vo;

import com.loongya.core.util.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

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
public class PsScaleMeasureRecordVo extends BaseVo {
    private Map<Integer,Integer> results;
    private Integer scaleId;
    private Integer userId;
}
