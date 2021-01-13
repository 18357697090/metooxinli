package com.metoo.order.nr.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loongya.core.util.*;
import com.loongya.core.util.aliyun.OSSUtil;
import com.loongya.core.util.order.OrderUtil;
import com.metoo.api.nr.NrGoodsApi;
import com.metoo.api.order.NrOrderInvestApi;
import com.metoo.order.nr.dao.entity.NrGoods;
import com.metoo.order.nr.dao.entity.NrOrderInvest;
import com.metoo.order.nr.service.NrGoodsService;
import com.metoo.order.nr.service.NrOrderInvestService;
import com.metoo.pojo.nr.model.NrGoodsModel;
import com.metoo.pojo.nr.vo.NrGoodsVo;
import com.metoo.pojo.nr.vo.NrOrderInvestVo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class NrOrderInvesApiImpl implements NrOrderInvestApi {

    @Resource
    private NrOrderInvestService nrOrderInvestService;

    /**
     * 创建订单
     * @param vo
     * @return
     */
    @Override
    public RE createInvestOrder(NrOrderInvestVo vo) {
        NrOrderInvest order  = new NrOrderInvest();
        order.setUpdateTime(new Date());
        order.setCreateTime(new Date());
        order.setUid(vo.getUid());
        order.setStatus(ConstantUtil.NrOrderInvestStatusEnum.WAIT_PAYING.getCode());
        order.setPrice(vo.getPrice().multiply(new BigDecimal(100)));
        order.setOrderNo(OrderUtil.createOrderNoForInvest(vo.getUid()));
        nrOrderInvestService.save(order);
        return RE.ok(order);
    }
}
