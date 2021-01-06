package com.metoo.ps.ps.api;

import com.loongya.core.util.CopyUtils;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsScaleMeasureRecordApi;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.pojo.old.model.Result;
import com.metoo.pojo.old.vo.MeasureRecordDTO;
import com.metoo.pojo.ps.model.PsScaleMeasureRecordModel;
import com.metoo.pojo.ps.vo.PsScaleMeasureRecordVo;
import com.metoo.pojo.tj.model.TjUserAccountModel;
import com.metoo.ps.ps.dao.entity.PsScale;
import com.metoo.ps.ps.dao.entity.PsScaleMeasureRecord;
import com.metoo.ps.ps.service.PsScaleMeasureRecordService;
import com.metoo.ps.ps.service.PsScaleService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
@Component
@DubboService
@Transactional
public class PsScaleMeasureRecordApiImpl implements PsScaleMeasureRecordApi {



    @Autowired
    private PsScaleMeasureRecordService psScaleMeasureRecordService;


    @Autowired
    private PsScaleService psScaleService;

    @DubboReference
    private TjUserAccountApi tjUserAccountApi;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public RE measureRecord(Integer uid, String time) {
        List<PsScaleMeasureRecord> measureRecords = psScaleMeasureRecordService.findBytime(time,uid);
        if(OU.isBlack(measureRecords)){
            return RE.noData();
        }
        List<PsScaleMeasureRecordModel> modelList = new ArrayList<>();
        for (PsScaleMeasureRecord e : measureRecords){
            PsScaleMeasureRecordModel model = CopyUtils.copy(e, new PsScaleMeasureRecordModel());
            modelList.add(model);
        }
        return RE.ok(modelList);
    }


    @Override
    public String result(PsScaleMeasureRecordVo vo) {

        psScaleMeasureRecordService.updateMeasure(vo.getUserId(),vo.getScaleId());
//        String achievement= "";
//        String achievement=CalculateTheScore.calculate(results); //todo.
//        Achievement achievement1=new Achievement();
//        achievement1.setAchievement(achievement);
//        achievement1.setUid(uid);
//        achievement1.setScaleId(results.getScaleId());
//        achievementDao.save(achievement1);
//        return achievement;
        return null;
    }

    @Override
    public RE pay(Integer uid, Integer scaleId) {

        PsScale scale = psScaleService.findByScaleId(scaleId);
        TjUserAccountModel zh=tjUserAccountApi.findByUid(uid);
        int x =zh.getBalance().compareTo(scale.getPrices());
        if(x >= 0) {
            BigDecimal balance = zh.getBalance().subtract(scale.getPrices());
            tjUserAccountApi.updateBalance(balance, uid);
            PsScaleMeasureRecord userAndMeasure=psScaleMeasureRecordService.findByUidAndScaleId(uid,scaleId);
            if(userAndMeasure==null){
                PsScaleMeasureRecord userAndMeasure1 = new PsScaleMeasureRecord();
                userAndMeasure1.setScaleId(scaleId);
                userAndMeasure1.setUid(uid);
                userAndMeasure1.setState(1);
                userAndMeasure1.setCount(1);
                psScaleMeasureRecordService.save(userAndMeasure1);
            }else {
                psScaleMeasureRecordService.updateCount(userAndMeasure.getCount()+1,uid,scaleId);
            }
            int number=scale.getNumber()+1;
            psScaleService.updateNumber(number,scaleId);
            return RE.ok("success");
        }else {
            return RE.fail("error");//error表示余额不足
        }
    }

    @Override
    public void updateMeasure(Integer userId, Integer scaleId) {
        psScaleMeasureRecordService.updateMeasure(userId, scaleId);
    }

    @Override
    public PsScaleMeasureRecordModel findByUserIdAndScaleId(Integer userId, Integer scaleId) {
        PsScaleMeasureRecord pojo = psScaleMeasureRecordService.findFirstByUidAndScaleIdOrderByCreateTimeDesc(userId, scaleId);
        PsScaleMeasureRecordModel model = CopyUtils.copy(pojo, new PsScaleMeasureRecordModel());
        return model;
    }

    @Override
    public void updateRecord(PsScaleMeasureRecordModel model) {
        PsScaleMeasureRecord pojo = CopyUtils.copy(model, new PsScaleMeasureRecord());
        psScaleMeasureRecordService.updateById(pojo);
    }
}
