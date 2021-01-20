package com.metoo.ps.ps.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loongya.core.exception.LoongyaException;
import com.loongya.core.util.*;
import com.loongya.core.util.aliyun.OSSUtil;
import com.metoo.api.ps.PsConsultOrderApi;
import com.metoo.api.ps.PsPourOutOrderApi;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.api.tj.TjUserAccountCoinDetailApi;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.pojo.ps.model.PsConsultModel;
import com.metoo.pojo.ps.model.PsConsultOrderModel;
import com.metoo.pojo.ps.model.PsPourOutModel;
import com.metoo.pojo.ps.model.PsPourOutOrderModel;
import com.metoo.pojo.ps.vo.PsConsultOrderVo;
import com.metoo.pojo.ps.vo.PsConsultVo;
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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private TjUserInfoApi tjUserInfoApi;


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
        if(accountModel.getPsCoin().compareTo(pojo.getPrice())<0){
            throw new LoongyaException("心理币不足");
        }
        // 修改余额
        tjUserAccountApi.updatePsCoin(pojo.getPrice(), vo.getUserId());
        // 明细添加  todo. need asyn
        TjUserAccountCoinDetailModel acModel = new TjUserAccountCoinDetailModel();
        acModel.setUid(vo.getUserId());
        acModel.setRemark("心理倾诉支出心理币");
        acModel.setContent("心理倾诉,支出" + pojo.getPrice() + "兔币" + ", 心理倾诉师id:{" + pojo.getId() + "}" + "心理倾诉师名称: {" + pojo.getName() + "}");
        acModel.setPrice(pojo.getPrice());
        acModel.setAccountId(accountModel.getId());
        acModel.setType(ConstantUtil.TjUserAccountCoinDetailTypeEnum.BUY_POUROUT.getCode());
        acModel.setTypeName(ConstantUtil.TjUserAccountCoinDetailTypeEnum.BUY_POUROUT.getMsg());
        acModel.setAfterPrice(accountModel.getPsCoin().subtract(pojo.getPrice()));
        acModel.setPrePrice(accountModel.getPsCoin());
        tjUserAccountCoinDetailApi.insertDetails(acModel);
        // 新增倾诉订单
        PsPourOutOrder pourPojo = new PsPourOutOrder();
        pourPojo.setUpdateTime(new Date());
        pourPojo.setStatus(1);
        pourPojo.setPrice(pojo.getPrice());
        pourPojo.setCreateTime(new Date());
        pourPojo.setUserId(vo.getUserId());
        pourPojo.setPourId(vo.getPourId());
        psPourOutOrderService.save(pourPojo);
        return RE.ok();
    }

    @Override
    public RE getPourOrderList(PsPourOutVo vo) {
        Page<PsPourOutOrder> page = new Page(vo.getPagenum(), vo.getPagesize());
        LambdaQueryWrapper<PsPourOutOrder> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PsPourOutOrder::getUserId, vo.getUserId());
        page = psPourOutOrderService.page(page, lqw);
        List<PsPourOutOrder> list = page.getRecords();
        if(OU.isBlack(list)){
            return RE.noData();
        }
        return REPage.ok(vo.getPagenum(), vo.getPagesize(), page.getTotal(), list.stream().flatMap(e->{
            PsPourOutOrderModel model = CopyUtils.copy(e, new PsPourOutOrderModel());
            model.setTjUserInfoModel(tjUserInfoApi.findByUid(model.getUserId()));
            PsPourOut psPourOut = psPourOutService.getById(model.getPourId());
            if(OU.isNotBlack(psPourOut)){
                PsPourOutModel psPourOutModel = CopyUtils.copy(psPourOut, new PsPourOutModel());
                psPourOutModel.setHeadImg(OSSUtil.fillPath(psPourOutModel.getHeadImg()));
                model.setPsPourOutModel(psPourOutModel);
            }
            return Stream.of(model);
        }).collect(Collectors.toList()));
    }

    @Override
    public String UnfinishedConsult(PsConsultVo vo) {
        PsPourOutOrder psPourOutOrder = psPourOutOrderService.UnfinishedConsult(vo);
        if (psPourOutOrder!=null){
            Date x = new Date();
            long y = x.getTime() - psPourOutOrder.getCreateTime().getTime();
            if(y<3600000){
                return"yes";
            }else {
                return "no";
            }
        }else {
            return "no";
        }
    }
}
