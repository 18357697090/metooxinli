package com.metoo.ps.ps.api;

import com.loongya.core.exception.LoongyaException;
import com.loongya.core.util.ConstantUtil;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsConsultApi;
import com.metoo.api.ps.PsConsultOrderApi;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.api.tj.TjUserAccountCoinDetailApi;
import com.metoo.pojo.ps.model.PsConsultModel;
import com.metoo.pojo.ps.vo.PsCapsuleVo;
import com.metoo.pojo.ps.vo.PsConsultOrderVo;
import com.metoo.pojo.tj.model.TjUserAccountCoinDetailModel;
import com.metoo.pojo.tj.model.TjUserAccountDetailAddDetailModel;
import com.metoo.pojo.tj.model.TjUserAccountModel;
import com.metoo.ps.ps.dao.entity.PsConsult;
import com.metoo.ps.ps.dao.entity.PsConsultOrder;
import com.metoo.ps.ps.service.PsConsultOrderService;
import com.metoo.ps.ps.service.PsConsultService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
}
