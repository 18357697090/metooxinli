package com.metoo.web.controller.tj;


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
@RequestMapping("/tj/tj-report")
public class TjReportController {

    //举报
    @PostMapping("/report")
    public String report(@RequestBody Report report, @RequestHeader("UID")Integer uid){
        report.setUid(uid);
        report.setState(0);
        Report report1 = reportDao.save(report);
        if(report1!=null){
            return "success";
        }else {
            return "error";
        }
    }

}
