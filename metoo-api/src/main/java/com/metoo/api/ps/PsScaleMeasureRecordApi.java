package com.metoo.api.ps;

import com.loongya.core.util.RE;
import com.metoo.pojo.old.model.Result;
import com.metoo.pojo.ps.model.PsScaleMeasureRecordModel;
import com.metoo.pojo.ps.vo.PsScaleMeasureRecordVo;
import com.metoo.pojo.ps.vo.PsScaleVo;

/**
 * <p>
 * 用户心理测量量表记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsScaleMeasureRecordApi {

    RE measureRecord(Integer uid, String time);

    String result(PsScaleMeasureRecordVo vo);

    RE pay(Integer uid, Integer scaleId);

    void updateMeasure(Integer userId, Integer scaleId);

    PsScaleMeasureRecordModel findByUserIdAndScaleId(Integer userId, Integer scaleId);

    void updateRecord(PsScaleMeasureRecordModel model);

    RE myScaleOrderList(PsScaleVo vo);
}
