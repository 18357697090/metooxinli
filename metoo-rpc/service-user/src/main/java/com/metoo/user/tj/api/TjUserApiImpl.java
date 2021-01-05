package com.metoo.user.tj.api;

import com.loongya.core.util.AssertUtils;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.tj.TjUserApi;
import com.metoo.pojo.login.model.LoginModel;
import com.metoo.pojo.login.model.LoginUserInfoModel;
import com.metoo.pojo.login.vo.LoginVo;
import com.metoo.pojo.old.model.LoginPojo;
import com.metoo.pojo.old.model.SecretGuardPojo;
import com.metoo.pojo.old.vo.FriendListDto;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.pojo.tj.model.TjUserModel;
import com.metoo.tools.CreateID;
import com.metoo.tools.zc;
import com.metoo.user.tj.dao.entity.TjSecretGuard;
import com.metoo.user.tj.dao.entity.TjUser;
import com.metoo.user.tj.dao.entity.TjUserAccount;
import com.metoo.user.tj.dao.entity.TjUserInfo;
import com.metoo.user.tj.service.TjSecretGuardService;
import com.metoo.user.tj.service.TjUserAccountService;
import com.metoo.user.tj.service.TjUserInfoService;
import com.metoo.user.tj.service.TjUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
public class TjUserApiImpl implements TjUserApi {

    @Autowired
    private TjUserService tjUserService;
    @Autowired
    private TjUserInfoService tjUserInfoService;
    @Autowired
    private TjUserAccountService tjUserAccountService;

    @Autowired
    private TjSecretGuardService tjSecretGuardService;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public RE fendFriend(Integer uid, String name) {

        List<TjUserInfo> userInfos = tjUserInfoService.findByNameLike(name);
        List<FriendListDto> friendListDtos = new ArrayList<>();
        for (TjUserInfo userInfo : userInfos){
            if (!uid.equals(userInfo.getUid())){
                FriendListDto friendListDto = mapper.map(userInfo,FriendListDto.class);
                friendListDtos.add(friendListDto);
            }
        }
        if (OU.isBlack(userInfos)){
            return RE.noData();
        }
        return RE.ok(friendListDtos);
    }

    @Override
    public RE register(LoginVo vo) {
        RE re = AssertUtils.checkParam(vo.getUsername(), vo.getPassword(), vo.getRepeatPassword());
        if(re.isFail()){
            return re;
        }
        zc zc=new zc();

        String username=vo.getUsername();
        TjUser a = tjUserService.findByUsername(username);
        if(a!=null){
            zc.setState("exist");
            return RE.ok(zc);
        }
        String password=vo.getPassword();
        int x= CreateID.create();
        TjUser b=tjUserService.getById(x);
        while (b!=null){
            x= CreateID.create();
            b=tjUserService.getById(x);
        }
        zc.setUid(x);
        TjUser user=new TjUser();
        user.setPassword(password);
        user.setUsername(username);
        tjUserService.save(user);
        TjUserAccount zh=new TjUserAccount();
        zh.setUid(x);
        tjUserAccountService.save(zh);
        TjSecretGuard secretGuard=new TjSecretGuard();
        secretGuard.setSecretGuard(vo.getSecret());
        secretGuard.setAnswer(vo.getAnswer());
        secretGuard.setUid(x);
        secretGuard.setUsername(username);
        tjSecretGuardService.save(secretGuard);
        zc.setState("success");
        return RE.ok(zc);
    }

    @Override
    public RE logIn(LoginVo vo) {
        LoginModel loginModel = tjUserService.login(vo);

        if(OU.isBlack(loginModel)){
            return RE.fail("账号或密码错误！");
        }
        if (loginModel.getState()!=0){
            return RE.fail("账号异常");
        }
        return RE.ok(loginModel);
    }

    @Override
    public RE modifyPassword(SecretGuardPojo secretGuardPojo) {

        TjSecretGuard secretGuard=tjSecretGuardService.findByUsername(secretGuardPojo.getUsername());
        String p1=secretGuardPojo.getAnswer();
        String p2=secretGuard.getAnswer();
        if(p1.equals(p2)){
            tjUserService.updateUserPassword(secretGuardPojo.getNewPassword(),secretGuardPojo.getUsername());
            return RE.ok();
        }else {
            return RE.fail("error");
        }
    }

    @Override
    public RE getUserInfo(Integer userId) {
        TjUserModel model  = getUserInfoInner(userId);
        if(OU.isBlack(model))
            return RE.fail("没有该用户信息！");
        return RE.ok(model);
    }

    private TjUserModel getUserInfoInner(Integer userId) {
        TjUser tjUser  = tjUserService.getById(userId);
        if(OU.isBlack(tjUser)){
            return null;
        }
        TjUserInfo tjUserInfo = tjUserInfoService.findUserInfoByUserId(userId);
        TjUserModel model = mapper.map(tjUser, TjUserModel.class);
        model.setTjUserInfoModel(mapper.map(tjUserInfo, TjUserInfoModel.class));
        model.setId(null);
        return model;
    }

}
