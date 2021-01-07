package com.metoo.ps.ps.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.loongya.core.util.CopyUtils;
import com.loongya.core.util.DateUtil;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.loongya.core.util.aliyun.OSSUtil;
import com.metoo.api.in.InBannerApi;
import com.metoo.api.ps.PsConsultApi;
import com.metoo.pojo.ps.model.PsConsultModel;
import com.metoo.pojo.ps.model.PsPourOutIndexModel;
import com.metoo.pojo.ps.model.PsPourOutModel;
import com.metoo.pojo.ps.vo.PsConsultVo;
import com.metoo.pojo.ps.vo.PsPourOutVo;
import com.metoo.ps.ps.dao.entity.*;
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
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 心理咨询师表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class PsConsultApiImpl implements PsConsultApi {

    @Autowired
    private PsConsultService psConsultService;
    @Autowired
    private PsConsultOrderService psConsultOrderService;

    @DubboReference
    private InBannerApi inBannerApi;

    @Override
    public RE psConsultBannerList(PsConsultVo vo) {
        return inBannerApi.inBannerList(2);
    }


    @Override
    public RE psConsultList(PsConsultVo vo) {
        Pageable pageable = PageRequest.of(vo.getPagenum(),vo.getPagesize(), Sort.Direction.DESC,"price");
        List<PsConsult> list =  psConsultService.findByOnLine(1,pageable);
        if(OU.isBlack(list)){
            return RE.noData();
        }
        List<PsConsultModel> modelList = list.stream().flatMap(e->{
            PsConsultModel model = CopyUtils.copy(e, new PsConsultModel());
            model.setHeadImg(OSSUtil.fillPath(model.getHeadImg()));
            pushConsultModel(model, vo.getUserId());
            return Stream.of(model);
        }).collect(Collectors.toList());
        return RE.ok(modelList);
    }

    private void pushConsultModel(PsConsultModel model, Integer userId) {
        // 查询当前倾诉师状态 1: 是否在进行中 2：进行中是否是自己的订单
        LambdaQueryWrapper<PsConsultOrder> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PsConsultOrder::getConId, model.getId());
        lqw.eq(PsConsultOrder::getStatus, 1);
        List<PsConsultOrder> list = psConsultOrderService.list(lqw);
        if(OU.isBlack(list)){
            model.setStatus(1);
        }else {
            boolean flag = false;
            for(PsConsultOrder or: list){
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

    @Override
    public RE psConsultDetail(PsConsultVo vo) {
        PsConsult pojo = psConsultService.getById(vo.getConId());
        Assert.isNull(pojo, "没有该咨询师！");
        PsConsultModel model = CopyUtils.copy(pojo, new PsConsultModel());
        model.setHeadImg(OSSUtil.fillPath(model.getHeadImg()));
        pushConsultModel(model, vo.getUserId());
        return RE.ok(model);
    }



}
