package com.metoo.ps.ps.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loongya.core.util.*;
import com.loongya.core.util.aliyun.OSSUtil;
import com.metoo.api.ps.PsConsultOrderApi;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.api.tj.TjUserAccountCoinDetailApi;
import com.metoo.api.tj.TjUserApi;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.pojo.ps.model.PsConsultModel;
import com.metoo.pojo.ps.model.PsConsultOrderModel;
import com.metoo.pojo.ps.vo.PsConsultOrderVo;
import com.metoo.pojo.ps.vo.PsConsultVo;
import com.metoo.pojo.tj.model.TjUserAccountCoinDetailModel;
import com.metoo.pojo.tj.model.TjUserAccountModel;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.ps.ps.dao.entity.PsConsult;
import com.metoo.ps.ps.dao.entity.PsConsultOrder;
import com.metoo.ps.ps.service.PsConsultOrderService;
import com.metoo.ps.ps.service.PsConsultService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class PsConsultOrderApiImpl implements PsConsultOrderApi {

    @Autowired
    private PsConsultOrderService psConsultOrderService;

    @Autowired
    private PsConsultService psConsultService;

    @DubboReference
    private TjUserAccountApi tjUserAccountApi;

    @DubboReference
    private TjUserInfoApi tjUserInfoApi;

    @DubboReference
    private TjUserAccountCoinDetailApi tjUserAccountCoinDetailApi;

    @Override
    public RE buyConsult(PsConsultOrderVo vo) {
        // 判断咨询师是否在线
        PsConsult psConsult = psConsultService.getById(vo.getConsultId());
        if(OU.isBlack(psConsult)||psConsult.getOnLine() == 0 ){
            return RE.fail("咨询师不在线");
        }
        // 判断用户余额是否充足
        TjUserAccountModel accountModel =tjUserAccountApi.findByUid(vo.getUserId());
        if(accountModel.getPsCoin().compareTo(psConsult.getPrice())<0){
            return RE.fail("心理币不足");
        }
        // 减余额
        tjUserAccountApi.updatePsCoin(psConsult.getPrice(), vo.getUserId());
        // 明细添加  todo. need asyn
        TjUserAccountCoinDetailModel acModel = new TjUserAccountCoinDetailModel();
        acModel.setUid(vo.getUserId());
        acModel.setRemark("心理咨询支出心理币");
        acModel.setContent("心理咨询,支出" + psConsult.getPrice() + "兔币" + ", 心理咨询师id:{" + psConsult.getId() + "}" + "心理咨询师名称: {" + psConsult.getName() + "}");
        acModel.setPrice(psConsult.getPrice());
        acModel.setAccountId(accountModel.getId());
        acModel.setType(ConstantUtil.TjUserAccountCoinDetailTypeEnum.BUY_CONSULT.getCode());
        acModel.setTypeName(ConstantUtil.TjUserAccountCoinDetailTypeEnum.BUY_CONSULT.getMsg());
        acModel.setAfterPrice(accountModel.getPsCoin().subtract(psConsult.getPrice()));
        acModel.setPrePrice(accountModel.getPsCoin());
        tjUserAccountCoinDetailApi.insertDetails(acModel);
        // 用户消费明细添加  todo. balance.
        // 新增咨询师订单
        PsConsultOrder order = new PsConsultOrder();
        order.setUpdateTime(new Date());
        order.setStatus(1);
        order.setPrice(psConsult.getPrice());
        order.setCreateTime(new Date());
        order.setUserId(vo.getUserId());
        order.setConId(psConsult.getId());
        psConsultOrderService.save(order);
        return RE.ok();
    }

    @Override
    public RE psConsulOrdertList(PsConsultVo vo) {
        Page<PsConsultOrder> page = new Page(vo.getPagenum(), vo.getPagesize());
        LambdaQueryWrapper<PsConsultOrder> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PsConsultOrder::getUserId, vo.getUserId());
        page = psConsultOrderService.page(page, lqw);
        List<PsConsultOrder> list = page.getRecords();
        if(OU.isBlack(list)){
            return RE.noData();
        }
        return REPage.ok(vo.getPagenum(), vo.getPagesize(), page.getTotal(), list.stream().flatMap(e->{
            PsConsultOrderModel model = CopyUtils.copy(e, new PsConsultOrderModel());
            model.setTjUserInfoModel(tjUserInfoApi.findByUid(model.getUserId()));
            PsConsult psConsult = psConsultService.getById(model.getConId());
            if(OU.isNotBlack(psConsult)){
                PsConsultModel psConsultModel = CopyUtils.copy(psConsult, new PsConsultModel());
                psConsultModel.setHeadImg(OSSUtil.fillPath(psConsultModel.getHeadImg()));
                model.setPsConsultModel(psConsultModel);
            }
            return Stream.of(model);
        }).collect(Collectors.toList()));
    }
}
