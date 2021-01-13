package com.metoo.order.nr.service.impl;

import com.alipay.api.AlipayApiException;
import com.loongya.core.exception.LoongyaException;
import com.loongya.core.util.ConstantUtil;
import com.loongya.core.util.OU;
import com.metoo.order.alipay.config.AlipayConfig;
import com.metoo.order.nr.dao.entity.NrOrderInvest;
import com.metoo.order.nr.dao.mapper.NrOrderInvestMapper;
import com.metoo.order.nr.dao.repository.NrOrderInvestRepository;
import com.metoo.order.nr.service.NrOrderInvestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metoo.pojo.nr.model.NrOrderInvestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 充值订单表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2021-01-13
 */
@Service
public class NrOrderInvestServiceImpl extends ServiceImpl<NrOrderInvestMapper, NrOrderInvest> implements NrOrderInvestService {

    @Autowired
    private NrOrderInvestRepository nrOrderInvestRepository;

    @Override
    public NrOrderInvest findFirstByOrderNo(String orderNo) {
        return nrOrderInvestRepository.findFirstByOrderNo(orderNo);
    }

    @Override
    public void investOrderSuccessBack(String out_trade_no) {
        NrOrderInvest pojo = nrOrderInvestRepository.findFirstByOrderNo(out_trade_no);
        if(OU.isBlack(pojo)){
            throw new LoongyaException("没有该订单号");
        }
        NrOrderInvest model = new NrOrderInvest();
        model.setId(pojo.getId());
        model.setPayTime(new Date());
        model.setUpdateTime(new Date());
        model.setStatus(ConstantUtil.NrOrderInvestStatusEnum.PAY_SUCCESS.getCode());
        this.updateById(model);
    }

}
