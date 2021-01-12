package com.metoo.user.tj.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.loongya.core.util.ConstantUtil;
import com.loongya.core.util.DateUtil;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.im.ImFriendApi;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.pojo.im.model.ImFriendModel;
import com.metoo.pojo.old.model.TjUserInfoPojoModel;
import com.metoo.pojo.old.vo.MeUserInfoDTO;
import com.metoo.pojo.old.vo.ModifyUserIfoDTO;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.pojo.tj.vo.TjUserInfoVo;
import com.metoo.tools.ReturnMessage;
import com.metoo.user.tj.dao.entity.TjUserInfo;
import com.metoo.user.tj.service.TjUserInfoService;
import io.netty.util.Constant;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Date;

/**
 * <p>
 * 用户个人信息表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class TjUserInfoApiImpl implements TjUserInfoApi {

    @Autowired
    private TjUserInfoService tjUserInfoService;

    @Autowired
    private DozerBeanMapper mapper;

    @DubboReference
    private ImFriendApi imFriendApi;


    @Override
    public RE upLoadUserInfo(TjUserInfoVo vo) {
        LambdaQueryWrapper<TjUserInfo> lqw = new LambdaQueryWrapper();
        lqw.eq(TjUserInfo::getUid, vo.getUserId());
        TjUserInfo model = tjUserInfoService.getOne(lqw);
        if(OU.isBlack(model)){
            model = new TjUserInfo();
            model.setUid(vo.getUserId());
            model.setNickName(vo.getNickName());
            model.setMotto(vo.getMotto());
            model.setGender(vo.getGender());
            model.setAge(DateUtil.getAge(vo.getBirthday()));
            model.setUpdateTime(new Date());
            model.setCreateTime(new Date());
            model.setHeadImg(vo.getHeadImg());
            model.setProv(vo.getProv());
            model.setProvCode(vo.getProvCode());
            model.setCity(vo.getCity());
            model.setCityCode(vo.getCityCode());
            model.setArea(vo.getArea());
            model.setAreaCode(vo.getAreaCode());
            model.setLevel(ConstantUtil.TjUserInfoLevel.ONE.getCode());
            tjUserInfoService.save(model);
        }else {
            if(OU.isNotBlack(vo.getNickName()))
                model.setNickName(vo.getNickName());
            if (OU.isNotBlack(vo.getGender()))
                model.setGender(vo.getGender());
            if(OU.isNotBlack(vo.getMotto()))
                model.setMotto(vo.getMotto());
            if(OU.isNotBlack(vo.getHeadImg()))
                model.setHeadImg(vo.getHeadImg());
            if(OU.isNotBlack(vo.getProv()))
                model.setProv(vo.getProv());
            if(OU.isNotBlack(vo.getProvCode()))
                model.setProvCode(vo.getProvCode());
            if(OU.isNotBlack(vo.getCity()))
                model.setCity(vo.getCity());
            if(OU.isNotBlack(vo.getCityCode()))
                model.setCityCode(vo.getCityCode());
            if(OU.isNotBlack(vo.getArea()))
                model.setArea(vo.getArea());
            if(OU.isNotBlack(vo.getAreaCode()))
                model.setAreaCode(vo.getAreaCode());
            if(OU.isNotBlack(vo.getBirthday()))
                model.setAge(DateUtil.getAge(vo.getBirthday()));
            model.setUpdateTime(new Date());
            tjUserInfoService.updateById(model);
        }
        return RE.ok();

    }


    @Override
    public RE modifyUserInfo(Integer uid, ModifyUserIfoDTO modifyUserIfoDTO) {
            ReturnMessage returnMessage = new ReturnMessage();
            int i = tjUserInfoService.updateUserInfo(modifyUserIfoDTO.getName(),modifyUserIfoDTO.getPicture(),modifyUserIfoDTO.getCity(),modifyUserIfoDTO.getMotto(),uid);
            if(i==1){
                returnMessage.setState("success");
                returnMessage.setExplain("修改成功");
            }else {
                returnMessage.setState("error");
                returnMessage.setExplain("修改失败");
            }
            return RE.ok(returnMessage);
    }

    @Override
    public RE userInfo(Integer uid1, Integer uid) {
            MeUserInfoDTO meUserInfoDTO = mapper.map(tjUserInfoService.findByUid(uid),MeUserInfoDTO.class);
            ImFriendModel friendModel = imFriendApi.findByUidAndFriendId(uid1, uid);
            if (OU.isBlack(meUserInfoDTO)){
                return RE.noData();
            }
            if (friendModel!=null){
                meUserInfoDTO.setState(friendModel.getState());
            }
            return RE.ok(meUserInfoDTO);
    }

    @Override
    public TjUserInfoModel findByUid(Integer uid) {
        return tjUserInfoService.findByUid(uid);
    }

}

