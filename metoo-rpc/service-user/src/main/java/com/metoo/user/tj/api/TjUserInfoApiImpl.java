package com.metoo.user.tj.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.im.ImFriendApi;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.pojo.im.model.ImFriendModel;
import com.metoo.pojo.old.model.TjUserInfoPojoModel;
import com.metoo.pojo.old.vo.MeUserInfoDTO;
import com.metoo.pojo.old.vo.ModifyUserIfoDTO;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.tools.ReturnMessage;
import com.metoo.user.tj.dao.entity.TjUserInfo;
import com.metoo.user.tj.service.TjUserInfoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

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
public class TjUserInfoApiImpl implements TjUserInfoApi {

    @Autowired
    private TjUserInfoService tjUserInfoService;

    @Autowired
    private DozerBeanMapper mapper;

    @DubboReference
    private ImFriendApi imFriendApi;

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

    @Override
    public RE upLoadUserInfo(TjUserInfoPojoModel userInfo, Integer uid) {
        TjUserInfoModel a= tjUserInfoService.findByUid(uid);
        TjUserInfo b= new TjUserInfo();
        if(a==null){
            b.setUid(uid);
            b.setAge(userInfo.getAge());
            b.setCity(userInfo.getCity());
            b.setDw(1);
            b.setGender(userInfo.getGender());
            b.setName(userInfo.getName());
            b.setPicture(userInfo.getPicture());
            tjUserInfoService.save(b);
            return RE.ok();
        }
        else {
            return RE.fail("error");
        }
    }
}

