package com.metoo.api.ps;

import com.loongya.core.util.RE;

/**
 * <p>
 * 用户心理测量量表记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsMeasureRecordApi {

    RE measureRecord(Integer uid, String time);
}
