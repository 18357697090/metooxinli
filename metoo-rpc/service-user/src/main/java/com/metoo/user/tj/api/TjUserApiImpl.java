package com.metoo.user.tj.api;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.loongya.core.util.*;
import com.loongya.core.util.aliyun.OSSUtil;
import com.metoo.api.tj.TjUserApi;
import com.metoo.pojo.login.model.LoginModel;
import com.metoo.pojo.login.vo.LoginUploadPasswordVo;
import com.metoo.pojo.login.vo.LoginVo;
import com.metoo.pojo.old.vo.FriendListDto;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.pojo.tj.model.TjUserModel;
import com.metoo.pojo.wechat.tj.login.model.WechatLoginModel;
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
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
@Transactional
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
    public RE findUserIdByExtendId(Integer extendId) {
        Integer userId = tjUserService.findByExtendId(extendId);
        if(OU.isBlack(userId))
            return RE.fail("没有该用户");
        return RE.ok(userId);
    }
    @Override
    public RE findByOpenId(String openId) {
        TjUser pojo = tjUserService.findByOpenId(openId);
        if(OU.isBlack(pojo))
            return RE.fail("没有该用户");
        return RE.ok(CopyUtils.copy(pojo, new TjUserModel()));
    }

    @Override
    public RE saveToken(WechatLoginModel model) {
        TjUser pojo = new TjUser();
        pojo.setId(model.getUid());
        pojo.setOpenId(model.getOpenId());
        tjUserService.updateById(pojo);
        return RE.ok();
    }

    @Override
    public RE register(LoginVo vo) {
        if(!vo.getPassword().equals(vo.getRepeatPassword())){
            return RE.fail("密码不相同,请重新输入密码！");
        }
        TjUser tjUser = tjUserService.findByUsername(vo.getUsername());
        if(OU.isNotBlack(tjUser)){
            return RE.fail("账号已存在，请重新输入账号！");
        }

        // 创建用户表
        TjUser pojo = new TjUser();
        pojo.setPassword(vo.getPassword());
        pojo.setUpdateTime(new Date());
        pojo.setExtendId(IdGenerator.getId());
        pojo.setOpenId(vo.getOpenId());
        pojo.setCreateTime(new Date());
        pojo.setUsername(vo.getUsername());
        pojo.setState(ConstantUtil.TjUserState.COMM.getCode());
        tjUserService.save(pojo);
        // 创建账户表
        TjUserAccount tjUserAccount = new TjUserAccount();
        tjUserAccount.setUid(pojo.getId());
        tjUserAccount.setBalance(new BigDecimal(0));
        tjUserAccount.setPsPoints(new BigDecimal(0));
        tjUserAccount.setAcPoints(new BigDecimal(0));
        tjUserAccount.setBalanceFrozen(new BigDecimal(0));
        tjUserAccountService.save(tjUserAccount);
        // 创建用户详细表
        TjUserInfo tjUserInfo = new TjUserInfo();
        tjUserInfo.setUid(pojo.getId());
        tjUserInfo.setHeadImg(ConstantUtil.HEAD_IMG_DEFAULT);
        tjUserInfo.setLevel(ConstantUtil.TjUserInfoLevel.ONE.getCode());
        tjUserInfo.setUpdateTime(new Date());
        tjUserInfo.setCreateTime(new Date());
        tjUserInfoService.save(tjUserInfo);
        if(OU.isNotBlack(vo.getSecret())){
            TjSecretGuard secretGuard=new TjSecretGuard();
            secretGuard.setSecretGuard(vo.getSecret());
            secretGuard.setAnswer(vo.getAnswer());
            secretGuard.setUid(pojo.getId());
            secretGuard.setUsername(pojo.getUsername());
            tjSecretGuardService.save(secretGuard);
        }
        LoginModel model = new LoginModel();
        model.setUserId(pojo.getId());
        model.setExtendId(pojo.getExtendId());
        return RE.ok(model);
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
    public RE modifyPassword(LoginUploadPasswordVo vo) {

        TjSecretGuard tjSecretGuard = tjSecretGuardService.findByUsername(vo.getUsername());
        if(OU.isBlack(tjSecretGuard)){
            return RE.fail("您没有设置密保问题，无法找回密码，请联系客服人员！");
        }
        if(!vo.getPassword().equals(vo.getRepeatPassword())){
            return RE.fail("密码不相同，请重新输入！");
        }
        if(vo.getAnswer().equals(tjSecretGuard.getAnswer()) && vo.getQuestion().equals(tjSecretGuard.getSecretGuard())){
            LambdaUpdateWrapper<TjUser> luw = new LambdaUpdateWrapper<>();
            luw.eq(TjUser::getId, tjSecretGuard.getUid());
            TjUser tjUser = new TjUser();
            tjUser.setPassword(vo.getPassword());
            tjUser.setUpdateTime(new Date());
            tjUserService.update(tjUser, luw);
            return RE.ok("密码修改成功，您可以重新登录啦！");
        }else {
            return RE.fail("密保答案不正确，如您忘记密保，请联系客服人员！！");
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
        model.getTjUserInfoModel().setHeadImg(OSSUtil.fillPath(model.getTjUserInfoModel().getHeadImg()));
        model.getTjUserInfoModel().setUid(null);
        model.setId(null);
        return model;
    }

}
