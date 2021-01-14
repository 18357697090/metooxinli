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
import com.metoo.pojo.tj.model.TjUserAccountCoinDetailModel;
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



}
