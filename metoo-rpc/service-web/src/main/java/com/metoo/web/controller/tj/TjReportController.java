package com.metoo.web.controller.tj;


import com.loongya.core.util.RE;
import com.metoo.api.tj.TjReportApi;
import com.metoo.pojo.tj.vo.TjReportVo;
import com.metoo.web.controller.order.NrBackpackController;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户举报表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/tj/tjReport")
public class TjReportController {

    @DubboReference
    private TjReportApi tjReportApi;
    //举报
    @PostMapping("/report")
    public RE report(@RequestBody TjReportVo report, @RequestHeader("UID")Integer uid){
        return tjReportApi.report(report, uid);
    }

}
