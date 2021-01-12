package com.metoo.ps.ps.api;

import com.loongya.core.exception.LoongyaException;
import com.loongya.core.util.ConstantUtil;
import com.loongya.core.util.CopyUtils;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsScaleMeasureRecordApi;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.api.tj.TjUserAccountCoinDetailApi;
import com.metoo.pojo.old.model.Result;
import com.metoo.pojo.old.vo.MeasureRecordDTO;
import com.metoo.pojo.ps.model.PsScaleMeasureRecordModel;
import com.metoo.pojo.ps.vo.PsScaleMeasureRecordVo;
import com.metoo.pojo.tj.model.TjUserAccountCoinDetailModel;
import com.metoo.pojo.tj.model.TjUserAccountDetailAddDetailModel;
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

    @DubboReference
    private TjUserAccountCoinDetailApi tjUserAccountCoinDetailApi;

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
        TjUserAccountModel accountModel =tjUserAccountApi.findByUid(uid);
        if(accountModel.getPsCoin().compareTo(scale.getPrices()) >= 0) {
            tjUserAccountApi.updatePsCoin(scale.getPrices(), uid);
            // 此处应该使用心理币购买
            // 用户账户详情添加 todo. need asyn
            TjUserAccountCoinDetailModel acModel = new TjUserAccountCoinDetailModel();
            acModel.setUid(uid);
            acModel.setRemark("购买心理测试支出心理币");
            acModel.setContent("购买心理测试,支出" + scale.getPrices() + "心理币" + ", 购买测量表id:{" + scale.getId() + "}" + "购买测量表名称: {" + scale.getName() + "}");
            acModel.setPrice(scale.getPrices());
            acModel.setAccountId(accountModel.getId());
            acModel.setType(ConstantUtil.TjUserAccountCoinDetailTypeEnum.BUY_PS.getCode());
            acModel.setTypeName(ConstantUtil.TjUserAccountCoinDetailTypeEnum.BUY_PS.getMsg());
            acModel.setAfterPrice(accountModel.getPsCoin().subtract(scale.getPrices()));
            acModel.setPrePrice(accountModel.getPsCoin());
            tjUserAccountCoinDetailApi.insertDetails(acModel);
            PsScaleMeasureRecord userAndMeasure=psScaleMeasureRecordService.findFirstByUidAndScaleIdOrderByCreateTimeDesc(uid,scaleId);
            if(userAndMeasure==null){
                PsScaleMeasureRecord userAndMeasure1 = new PsScaleMeasureRecord();
                userAndMeasure1.setScaleId(scaleId);
                userAndMeasure1.setUid(uid);
                userAndMeasure1.setState(ConstantUtil.PsScaleMeasureRecordStateEnum.BUY_NOT_USE.getCode());
                userAndMeasure1.setCount(1);
                psScaleMeasureRecordService.save(userAndMeasure1);
            }else {
                psScaleMeasureRecordService.updateCount(userAndMeasure.getScaleId());
            }
            psScaleService.updateNumber(scaleId);
            return RE.ok();
        }else {
            return RE.fail("余额不足");//error表示余额不足
        }
    }

    @Override
    public void updateMeasure(Integer userId, Integer scaleId) {
        psScaleMeasureRecordService.updateMeasure(userId, scaleId);
    }

    @Override
    public PsScaleMeasureRecordModel findByUserIdAndScaleId(Integer userId, Integer scaleId) {
        PsScaleMeasureRecord pojo = psScaleMeasureRecordService.findFirstByUidAndScaleIdOrderByCreateTimeDesc(userId, scaleId);
        if(OU.isBlack(pojo)){
            throw new LoongyaException("数据异常");
        }
        PsScaleMeasureRecordModel model = CopyUtils.copy(pojo, new PsScaleMeasureRecordModel());
        return model;
    }

    @Override
    public void updateRecord(PsScaleMeasureRecordModel model) {
        PsScaleMeasureRecord pojo = CopyUtils.copy(model, new PsScaleMeasureRecord());
        psScaleMeasureRecordService.updateById(pojo);
    }
}
