package com.metoo.ps.ps.api;

import com.loongya.core.exception.LoongyaException;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsConsultOrderApi;
import com.metoo.api.ps.PsPourOutOrderApi;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.pojo.ps.vo.PsConsultOrderVo;
import com.metoo.pojo.ps.vo.PsPourOutVo;
import com.metoo.ps.ps.dao.entity.PsConsult;
import com.metoo.ps.ps.dao.entity.PsConsultOrder;
import com.metoo.ps.ps.dao.entity.PsPourOut;
import com.metoo.ps.ps.dao.entity.PsPourOutOrder;
import com.metoo.ps.ps.service.PsConsultOrderService;
import com.metoo.ps.ps.service.PsConsultService;
import com.metoo.ps.ps.service.PsPourOutOrderService;
import com.metoo.ps.ps.service.PsPourOutService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 心理咨询订单表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class PsPourOutOrderApiImpl implements PsPourOutOrderApi {

    @Autowired
    private PsPourOutOrderService psPourOutOrderService;
    @Autowired
    private PsPourOutService psPourOutService;

    @DubboReference
    private TjUserAccountApi tjUserAccountApi;

    @Override
    public RE buyPour(PsPourOutVo vo) {
        // 获取余额
        RE re = tjUserAccountApi.findBalance(vo.getUserId());
        BigDecimal balance = (BigDecimal) re.getData();
        // 获取倾诉师信息
        PsPourOut pojo = psPourOutService.getById(vo.getPourId());
        // 判断倾诉师是否在线
        if(pojo.getOnLine() == 0){
            throw new LoongyaException("该倾诉师已经下线！");
        }
        // 判断余额是否充足
        if(balance.compareTo(pojo.getPrices())<0){
            throw new LoongyaException("余额不足");
        }
        // 修改余额
        tjUserAccountApi.updateBalance(pojo.getPrices(), vo.getUserId());
        // 新增倾诉订单
        PsPourOutOrder pourPojo = new PsPourOutOrder();
        pourPojo.setUpdateTime(new Date());
        pourPojo.setStatus(1);
        pourPojo.setPrice(pojo.getPrices());
        pourPojo.setCreateTime(new Date());
        pourPojo.setUserId(vo.getUserId());
        pourPojo.setPourId(vo.getPourId());
        psPourOutOrderService.save(pourPojo);
        return RE.ok();
    }
}
