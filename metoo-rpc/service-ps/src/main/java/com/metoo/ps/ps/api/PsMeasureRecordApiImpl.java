package com.metoo.ps.ps.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsMeasureRecordApi;
import com.metoo.pojo.old.vo.MeasureRecordDTO;
import com.metoo.ps.ps.dao.entity.PsMeasureRecord;
import com.metoo.ps.ps.service.PsMeasureRecordService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户心理测量量表记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public class PsMeasureRecordApiImpl implements PsMeasureRecordApi {



    @Autowired
    private PsMeasureRecordService psMeasureRecordService;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public RE measureRecord(Integer uid, String time) {

        List<PsMeasureRecord> measureRecords = psMeasureRecordService.findBytime(time,uid);
        List<MeasureRecordDTO> measureRecordDTOS = new ArrayList<>();
        for (PsMeasureRecord measureRecord : measureRecords){
            MeasureRecordDTO measureRecordDTO = mapper.map(measureRecord,MeasureRecordDTO.class);
            measureRecordDTOS.add(measureRecordDTO);
        }
        if(OU.isBlack(measureRecordDTOS)){
            return RE.noData();
        }
        return RE.ok(measureRecordDTOS);
    }
}
