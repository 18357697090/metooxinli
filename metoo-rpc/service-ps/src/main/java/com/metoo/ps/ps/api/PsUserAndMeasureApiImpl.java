package com.metoo.ps.ps.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsUserAndMeasureApi;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.pojo.old.vo.MeasureRecordDTO;
import com.metoo.pojo.tj.model.TjUserAccountModel;
import com.metoo.ps.ps.dao.entity.PsMeasureRecord;
import com.metoo.ps.ps.dao.entity.PsScale;
import com.metoo.ps.ps.dao.entity.PsUserAndMeasure;
import com.metoo.ps.ps.service.PsMeasureRecordService;
import com.metoo.ps.ps.service.PsScaleService;
import com.metoo.ps.ps.service.PsUserAndMeasureService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户心理测量记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
public class PsUserAndMeasureApiImpl implements PsUserAndMeasureApi {

    @Autowired
    private PsScaleService psScaleService;

    @DubboReference
    private TjUserAccountApi tjUserAccountApi;

    @Autowired
    private PsUserAndMeasureService psUserAndMeasureService;

    @Override
    public RE pay(Integer uid, Integer scaleId) {

        PsScale scale = psScaleService.findByScaleId(scaleId);
        TjUserAccountModel zh=tjUserAccountApi.findByUid(uid);
        int x =zh.getBalance().compareTo(new BigDecimal(scale.getPrices()));
        if(x >= 0) {
            BigDecimal balance = zh.getBalance().subtract(new BigDecimal(scale.getPrices()));
            tjUserAccountApi.updateBalance(balance, uid);
            PsUserAndMeasure userAndMeasure=psUserAndMeasureService.findByUidAndScaleId(uid,scaleId);
            if(userAndMeasure==null){
                PsUserAndMeasure userAndMeasure1 = new PsUserAndMeasure();
                userAndMeasure1.setScaleId(scaleId);
                userAndMeasure1.setUid(uid);
                userAndMeasure1.setState(1);
                userAndMeasure1.setCount(1);
                psUserAndMeasureService.save(userAndMeasure1);
            }else {
                psUserAndMeasureService.updateCount(userAndMeasure.getCount()+1,uid,scaleId);
            }
            int number=Integer.parseInt(scale.getNumber())+1;
            psScaleService.updateNumber(number,scaleId);
            return RE.ok("success");
        }else {
            return RE.fail("error");//error表示余额不足
        }
    }
}
