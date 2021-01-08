package com.metoo.ps.ps.api;

import com.loongya.core.exception.LoongyaException;
import com.loongya.core.util.ConstantUtil;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsConsultOrderApi;
import com.metoo.api.ps.PsPourOutOrderApi;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.api.tj.TjUserAccountCoinDetailApi;
import com.metoo.pojo.ps.vo.PsConsultOrderVo;
import com.metoo.pojo.ps.vo.PsPourOutVo;
import com.metoo.pojo.tj.model.TjUserAccountCoinDetailModel;
import com.metoo.pojo.tj.model.TjUserAccountDetailAddDetailModel;
import com.metoo.pojo.tj.model.TjUserAccountModel;
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
 * 倾诉师表 服务类
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


    @DubboReference
    private TjUserAccountCoinDetailApi tjUserAccountCoinDetailApi;

    @Override
    public RE buyPour(PsPourOutVo vo) {
        // 获取倾诉师信息
        PsPourOut pojo = psPourOutService.getById(vo.getPourId());
        // 判断倾诉师是否在线
        if(pojo.getOnLine() == 0){
            throw new LoongyaException("该倾诉师已经下线！");
        }
        // 判断用户余额是否充足
        TjUserAccountModel accountModel =tjUserAccountApi.findByUid(vo.getUserId());
        if(accountModel.getPsCoin().compareTo(pojo.getPrices())<0){
            throw new LoongyaException("心理币不足");
        }
        // 修改余额
        tjUserAccountApi.updatePsCoin(pojo.getPrices(), vo.getUserId());
        // 明细添加  todo. need asyn
        TjUserAccountCoinDetailModel acModel = new TjUserAccountCoinDetailModel();
        acModel.setUid(vo.getUserId());
        acModel.setRemark("心理倾诉支出心理币");
        acModel.setContent("心理倾诉,支出" + pojo.getPrices() + "兔币" + ", 心理倾诉师id:{" + pojo.getId() + "}" + "心理倾诉师名称: {" + pojo.getName() + "}");
        acModel.setPrice(pojo.getPrices());
        acModel.setAccountId(accountModel.getId());
        acModel.setType(ConstantUtil.TjUserAccountCoinDetailTypeEnum.BUY_POUROUT.getCode());
        acModel.setTypeName(ConstantUtil.TjUserAccountCoinDetailTypeEnum.BUY_POUROUT.getMsg());
        acModel.setAfterPrice(accountModel.getPsCoin().subtract(pojo.getPrices()));
        acModel.setPrePrice(accountModel.getPsCoin());
        tjUserAccountCoinDetailApi.insertDetails(acModel);
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
