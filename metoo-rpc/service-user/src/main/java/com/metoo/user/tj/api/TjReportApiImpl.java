package com.metoo.user.tj.api;

import com.loongya.core.util.RE;
import com.metoo.api.tj.TjReportApi;
import com.metoo.pojo.tj.vo.TjReportVo;
import com.metoo.user.tj.dao.entity.TjReport;
import com.metoo.user.tj.service.TjReportService;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户举报表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
public class TjReportApiImpl implements TjReportApi {

    @Autowired
    private TjReportService tjReportService;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public RE report(TjReportVo report, Integer uid) {
        report.setUid(uid);
        report.setState(0);
        tjReportService.save(mapper.map(report, TjReport.class));
        return RE.ok();
    }
}
