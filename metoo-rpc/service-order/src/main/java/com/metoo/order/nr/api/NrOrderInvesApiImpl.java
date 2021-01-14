package com.metoo.order.nr.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loongya.core.exception.LoongyaException;
import com.loongya.core.util.*;
import com.loongya.core.util.aliyun.OSSUtil;
import com.loongya.core.util.order.OrderUtil;
import com.metoo.api.nr.NrGoodsApi;
import com.metoo.api.order.NrOrderInvestApi;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.api.tj.TjUserAccountCoinDetailApi;
import com.metoo.order.nr.dao.entity.NrGoods;
import com.metoo.order.nr.dao.entity.NrOrderInvest;
import com.metoo.order.nr.service.NrGoodsService;
import com.metoo.order.nr.service.NrOrderInvestService;
import com.metoo.pojo.nr.model.NrGoodsModel;
import com.metoo.pojo.nr.vo.NrGoodsVo;
import com.metoo.pojo.nr.vo.NrOrderInvestVo;
import com.metoo.pojo.tj.model.TjUserAccountCoinDetailModel;
import com.metoo.pojo.tj.model.TjUserAccountModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

    @DubboReference
    private TjUserAccountApi tjUserAccountApi;

    @DubboReference
    private TjUserAccountCoinDetailApi tjUserAccountCoinDetailApi;

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

    @Override
    public RE investOrderSuccessBack(String out_trade_no) {
        NrOrderInvest pojo = nrOrderInvestService.findFirstByOrderNo(out_trade_no);
        if(OU.isBlack(pojo)){
            throw new LoongyaException("没有该订单号");
        }
        NrOrderInvest model = new NrOrderInvest();
        model.setId(pojo.getId());
        model.setPayTime(new Date());
        model.setUpdateTime(new Date());
        model.setStatus(ConstantUtil.NrOrderInvestStatusEnum.PAY_SUCCESS.getCode());
        nrOrderInvestService.updateById(model);
        // 新增心理币
        TjUserAccountModel accountModel = tjUserAccountApi.findByUid(pojo.getUid());
        BigDecimal total = pojo.getPrice().divide(new BigDecimal(100), 2, RoundingMode.HALF_DOWN);
        tjUserAccountApi.updateBalanceUp(total, pojo.getUid());
        // 明细添加  todo. need asyn
        TjUserAccountCoinDetailModel acModel = new TjUserAccountCoinDetailModel();
        acModel.setUid(pojo.getUid());
        acModel.setRemark("充值心理币");
        acModel.setContent("充值心理币,收入" + total + "心理币" + ", 订单编号orderNO:{" + pojo.getOrderNo() + "}");
        acModel.setPrice(total);
        acModel.setAccountId(accountModel.getId());
        acModel.setType(ConstantUtil.TjUserAccountCoinDetailTypeEnum.INVEST_GOODS.getCode());
        acModel.setTypeName(ConstantUtil.TjUserAccountCoinDetailTypeEnum.INVEST_GOODS.getMsg());
        acModel.setAfterPrice(accountModel.getPsCoin().add(total));
        acModel.setPrePrice(accountModel.getPsCoin());
        tjUserAccountCoinDetailApi.insertDetails(acModel);
        return RE.ok();
    }
}
