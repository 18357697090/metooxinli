package com.metoo.web.controller.ps;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户心理测量量表记录表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/ps-measure-record")
public class PsMeasureRecordController {

    @GetMapping("/measureRecord")
    public List<MeasureRecordDTO> measureRecord(@RequestHeader("UID")Integer uid, String time){
        List<MeasureRecord> measureRecords = measureRecordDao.findBytime(time,uid);
        List<MeasureRecordDTO> measureRecordDTOS = new ArrayList<>();
        for (MeasureRecord measureRecord : measureRecords){
            MeasureRecordDTO measureRecordDTO = mapper.map(measureRecord,MeasureRecordDTO.class);
            measureRecordDTOS.add(measureRecordDTO);
        }
        return measureRecordDTOS;
    }

}
