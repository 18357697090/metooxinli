package com.metoo.pojo.ps.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 心理倾诉师表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PsPourOutIndexModel implements Serializable {
    private List<PsCapsuleDetailModel> psCapsuleDetailModelList;
    private List<PsPourOutModel> pourOuts;
}
