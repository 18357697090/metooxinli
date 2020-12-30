package com.metoo.api.tj;

import com.loongya.core.util.RE;
import com.metoo.pojo.tj.vo.TjReportVo;

/**
 * <p>
 * 用户举报表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface TjReportApi {

    RE report(TjReportVo report, Integer uid);
}
