package com.metoo.ps.ps.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loongya.core.exception.LoongyaException;
import com.loongya.core.util.*;
import com.loongya.core.util.aliyun.OSSUtil;
import com.metoo.api.in.InBannerApi;
import com.metoo.api.order.PsCapsuleOrderApi;
import com.metoo.api.ps.PsCapsuleApi;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.pojo.old.model.FindCapsuleByIdPojo;
import com.metoo.pojo.old.model.PourOutCapsulePojo;
import com.metoo.pojo.old.model.SaveCapsulePojo;
import com.metoo.pojo.order.model.PsCapsuleOrderModel;
import com.metoo.pojo.ps.model.PsCapsuleDetailModel;
import com.metoo.pojo.ps.model.PsCapsuleModel;
import com.metoo.pojo.ps.vo.PsCapsuleVo;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.ps.ps.dao.entity.PsCapsule;
import com.metoo.ps.ps.dao.entity.PsCapsuleImg;
import com.metoo.ps.ps.service.PsCapsuleImgService;
import com.metoo.ps.ps.service.PsCapsuleService;
import com.metoo.tools.CreateID;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 心理倾诉胶囊（备忘录功能）表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class PsCapsuleApiImpl implements PsCapsuleApi {

    @Autowired
    private PsCapsuleService psCapsuleService;

    @Autowired
    private PsCapsuleImgService psCapsuleImgService;

    @DubboReference
    private TjUserInfoApi tjUserInfoApi;

    @DubboReference
    private PsCapsuleOrderApi psCapsuleOrderApi;

    @Autowired
    private DozerBeanMapper mapper;

    @DubboReference
    private InBannerApi inBannerApi;


    @Override
    public RE psCapsuleIndexBannerList(PsCapsuleVo vo) {
        return inBannerApi.inBannerList(3);
    }


    @Override
    public PsCapsuleModel findByCapsuleId(Integer capsuleId) {
        PsCapsule pojo = psCapsuleService.findByCapsuleId(capsuleId);
        if(OU.isBlack(pojo)){
            return null;
        }
        PsCapsuleModel model = mapper.map(pojo, PsCapsuleModel.class);

        return model;

    }

    @Override
    public RE modifyCapsule(Integer state, Integer capsuleId) {

        //state  0表示隐藏  1表示可见状态  2表示删除胶囊
        if(state == 2){
            PsCapsule pojo = new PsCapsule();
            pojo.setId(capsuleId);
            pojo.setState(2);
            psCapsuleService.updateById(pojo);
        }else if (state == 1|| state == 0){
            PsCapsule pojo = new PsCapsule();
            pojo.setId(capsuleId);
            pojo.setAuthType(state);
            psCapsuleService.updateById(pojo);
        }
        return RE.ok();
    }


    @Override
    public RE myCapsule(PsCapsuleVo vo) {
        Page<PsCapsule> page = new Page<>(vo.getPagenum(), vo.getPagesize());
        LambdaQueryWrapper<PsCapsule> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PsCapsule::getUid, vo.getUserId());
        return getCapsuleList(vo, page, lqw);
    }

    private RE getCapsuleList(PsCapsuleVo vo,Page<PsCapsule> page, LambdaQueryWrapper<PsCapsule> lqw) {
        page = psCapsuleService.page(page, lqw);
        List<PsCapsule> capsuleList = page.getRecords();
        return REPage.ok(vo.getPagenum(), vo.getPagesize(), (int)page.getTotal(), capsuleList.stream().flatMap(e->{
            PsCapsuleDetailModel model = getDetailModel(e, vo.getUserId());
            return Stream.of(model);
        }).collect(Collectors.toList()));
    }


    @Override
    public RE psCapsuleHostListMore(PsCapsuleVo vo) {
        Page<PsCapsule> page = new Page<>(vo.getPagenum(), vo.getPagesize());
        LambdaQueryWrapper<PsCapsule> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PsCapsule::getAuthType, 1);
        lqw.eq(PsCapsule::getState, 0);
        return getCapsuleList(vo, page, lqw);
    }


    @Override
    public RE psCapsuleIndexList(PsCapsuleVo vo) {
        List<PsCapsule> capsuleList = psCapsuleService.findCapsuleRand(3);
        return RE.ok(capsuleList.stream().flatMap(e->{
            PsCapsuleDetailModel model = getDetailModel(e, vo.getUserId());
            return Stream.of(model);
        }).collect(Collectors.toList()));
    }

    @Override
    public RE saveCapsule(PsCapsuleVo vo) {
        PsCapsule pojo = new PsCapsule();
        pojo.setState(ConstantUtil.CommStatusEnum.NORMAL.getCode());
        pojo.setAuthType(vo.getAuthType());
        pojo.setContent(vo.getContent());
        pojo.setTitle(vo.getTitle());
        pojo.setPrice(vo.getPrice());
        pojo.setUid(vo.getUserId());
        pojo.setUpdateTime(new Date());
        pojo.setReadNum(0);
        pojo.setCreateTime(new Date());
        psCapsuleService.save(pojo);
        if(OU.isBlack(pojo.getId())){
            throw new LoongyaException(CommsEnum.SAVE_FAIL);
        }
        if(OU.isNotBlack(vo.getImgs())){
            List<String> imgList = new ArrayList<>(Arrays.asList(vo.getImgs().split(",")));
            imgList.stream().forEach(e->{
                PsCapsuleImg imgPojo = new PsCapsuleImg();
                imgPojo.setCapId(pojo.getId());
                imgPojo.setImg(e);
                psCapsuleImgService.save(imgPojo);
            });
        }
        return RE.ok();
    }

    @Override
    public RE findCapsuleDetailById(Integer capsuleId, Integer uid) {
        PsCapsule capsule = psCapsuleService.findByCapsuleId(capsuleId);
        Assert.isNull(capsule, "没有该胶囊");
        // 胶囊观看量加一
        psCapsuleService.updateReadNum(capsuleId);
        return RE.ok(getDetailModel(capsule, uid));
    }


    private PsCapsuleDetailModel getDetailModel(PsCapsule capsule, Integer uid) {
        PsCapsuleDetailModel model = new PsCapsuleDetailModel();
        model.setPrice(capsule.getPrice());
        // 查询胶囊用户信息
        TjUserInfoModel byUid = tjUserInfoApi.findByUid(capsule.getUid());
        model.setHeadImg(OSSUtil.fillPath(byUid.getHeadImg()));
        model.setNickName(byUid.getNickName());
        if(capsule.getUid() == uid){
            model.setIsSelf(true);
        }else {
            model.setIsSelf(false);
        }
        // 判断胶囊是否公开
        if(capsule.getAuthType() == 0){ // ==0：私密胶囊
            // 只有自己才能访问私密胶囊
            if(uid == capsule.getUid()){
                // 如果为公开的，封装胶囊和图片信息
                pushModel(model, capsule);
            }
        }else { // 公开胶囊
            // 如果为公开胶囊，查看价格是否=0
            if(capsule.getPrice().compareTo(new BigDecimal(0)) <= 0){
                // 如果价格=0, 封装胶囊和图片信息
                pushModel(model, capsule);
            }else {
                // 价格!=0，查看是否是自己的
                if(capsule.getUid() == uid){
                    // 如果是自己的，封装胶囊和图片信息
                    pushModel(model, capsule);
                }else {
                    // 不是自己的，查看是否已经购买过
                    PsCapsuleOrderModel orderModel = psCapsuleOrderApi.findByUidAndCapsuleId(uid, capsule.getId());
                    if(OU.isNotBlack(orderModel)){
                        // 如果已经购买过，封装胶囊和图片信息
                        pushModel(model, capsule);
                    }
                }
            }
        }
        return model;
    }

    private void pushModel(PsCapsuleDetailModel model, PsCapsule capsule) {
        CopyUtils.copy(capsule, model);
        List<PsCapsuleImg> imgList = psCapsuleImgService.findImgListByCapId(capsule.getId());
        if(OU.isNotBlack(imgList)){
            model.setImgList(imgList.stream().flatMap(e->{
                return Stream.of(OSSUtil.fillPath(e.getImg()));
            }).collect(Collectors.toList()));
        }
        model.setCreateTimeStr(DateUtil.format(capsule.getCreateTime(), "yyyy月MM日dd"));
    }
}
