package com.metoo.ps.ps.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.loongya.core.util.CopyUtils;
import com.loongya.core.util.DateUtil;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.loongya.core.util.aliyun.OSSUtil;
import com.metoo.api.ps.PsCapsuleApi;
import com.metoo.api.ps.PsPourOutApi;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.pojo.old.model.PourOutCapsulePojo;
import com.metoo.pojo.old.model.PourOutPojo;
import com.metoo.pojo.ps.model.PsCapsuleDetailModel;
import com.metoo.pojo.ps.model.PsPourOutIndexModel;
import com.metoo.pojo.ps.model.PsPourOutModel;
import com.metoo.pojo.ps.vo.PsPourOutVo;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.ps.ps.dao.entity.PsCapsule;
import com.metoo.ps.ps.dao.entity.PsPourOut;
import com.metoo.ps.ps.dao.entity.PsPourOutOrder;
import com.metoo.ps.ps.service.PsCapsuleService;
import com.metoo.ps.ps.service.PsPourOutOrderService;
import com.metoo.ps.ps.service.PsPourOutService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 心理倾诉师表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class PsPourOutApiImpl implements PsPourOutApi {

    @Autowired
    private PsPourOutService psPourOutService;

    @Autowired
    private PsCapsuleService psCapsuleService;
    @Autowired
    private PsPourOutOrderService psPourOutOrderService;

    @DubboReference
    private TjUserInfoApi tjUserInfoApi;

    @Autowired
    private PsCapsuleApi psCapsuleApi;

    @Override
    public RE getPourList(PsPourOutVo vo) {
        PsPourOutIndexModel model = new PsPourOutIndexModel();
        Pageable pageable = PageRequest.of(vo.getPagenum(),vo.getPagesize(), Sort.Direction.DESC,"prices");
        List<PsPourOutModel> modelList = psPourOutService.findByOnLine(1, pageable);
        modelList.stream().forEach(e->{
            e.setPicture(OSSUtil.fillPath(e.getPicture()));
            // 查询当前倾诉师状态 1: 是否在进行中 2：进行中是否是自己的订单
            pushPourOutModel(e, vo.getUserId());
        });
        model.setPourOuts(modelList);
        List<PsCapsule> capsule= psCapsuleService.findCapsule();
        model.setPsCapsuleDetailModelList(this.pushModel(capsule));
        return RE.ok(model);
    }

    @Override
    public RE getPourDetail(PsPourOutVo vo) {
        PsPourOut pojo = psPourOutService.getById(vo.getPourId());
        Assert.isNull(pojo, "没有该倾诉师！");
        PsPourOutModel model = CopyUtils.copy(pojo, new PsPourOutModel());
        model.setPicture(OSSUtil.fillPath(model.getPicture()));
        pushPourOutModel(model, vo.getUserId());
        return RE.ok(model);
    }

    private void pushPourOutModel(PsPourOutModel model, Integer userId) {
        // 查询当前倾诉师状态 1: 是否在进行中 2：进行中是否是自己的订单
        LambdaQueryWrapper<PsPourOutOrder> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PsPourOutOrder::getPourId, model.getId());
        lqw.eq(PsPourOutOrder::getStatus, 1);
        List<PsPourOutOrder> list = psPourOutOrderService.list(lqw);
        if(OU.isBlack(list)){
            model.setStatus(1);
        }else {
            boolean flag = false;
            for(PsPourOutOrder or: list){
                if(or.getUserId() == userId){
                    if(DateUtil.diffMin(new Date(), or.getCreateTime()) <= 60){
                        flag = true;
                    }
                }
            }
            if(flag){ // 有自己的未完成的订单
                model.setStatus(3);
            }else {
                model.setStatus(2);
            }
        }
    }


    private List<PsCapsuleDetailModel> pushModel(List<PsCapsule> capsules){
        List<PsCapsuleDetailModel> modelList = new ArrayList<>();
        for (PsCapsule e : capsules) {
            PsCapsuleDetailModel model = new PsCapsuleDetailModel();
            CopyUtils.copy(e, model);
            TjUserInfoModel userInfo = tjUserInfoApi.findByUid(e.getUid());
            model.setNickName(userInfo.getNickName());
            model.setHeadImg(OSSUtil.fillPath(userInfo.getHeadImg()));
            modelList.add(model);
        }
        return modelList;
    }
}
