package com.metoo.user.tj.api;

import com.loongya.core.exception.LoongyaException;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.pojo.old.vo.MeDTO;
import com.metoo.pojo.tj.model.TjUserAccountModel;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.user.tj.dao.entity.TjUserAccount;
import com.metoo.user.tj.service.TjUserAccountService;
import com.metoo.user.tj.service.TjUserInfoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <p>
 * 用户账户表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class TjUserAccountApiImpl implements TjUserAccountApi {

    @Autowired
    private TjUserAccountService tjUserAccountService;

    @Autowired
    private TjUserInfoService tjUserInfoService;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Override
    public TjUserAccountModel findByUid(Integer uid) {
        TjUserAccount pojo = tjUserAccountService.findByUid(uid);
        TjUserAccountModel model = dozerBeanMapper.map(pojo, TjUserAccountModel.class);
        return model;
    }

    @Override
    public void updateBalance(BigDecimal subtract, Integer uid) {
        tjUserAccountService.updateBalance(subtract, uid);
    }

    @Override
    public RE me(Integer uid) {

        TjUserAccount zh = tjUserAccountService.findByUid(uid);
        TjUserInfoModel userInfo = tjUserInfoService.findByUid(uid);
        MeDTO meDTO = new MeDTO();
        meDTO.setMotto(userInfo.getMotto());
        meDTO.setPicture(userInfo.getHeadImg());
        meDTO.setLevel(userInfo.getLevel());
        meDTO.setName(userInfo.getNickName());
        meDTO.setActiveIntegral(zh.getAcPoints());
        meDTO.setPsychologyIntegral(zh.getPsPoints());
        meDTO.setBalance(zh.getBalance());
        return RE.ok(meDTO);
    }

    @Override
    public RE findzh(Integer uid) {
        TjUserAccount byUid = tjUserAccountService.findByUid(uid);
        if(OU.isBlack(byUid)){
            return RE.fail("没有数据");
        }
        return RE.ok(dozerBeanMapper.map(byUid, TjUserAccountModel.class));
    }

    @Override
    public RE findBalance(Integer uid) {
        TjUserAccount zh=tjUserAccountService.findByUid(uid);
        if(OU.isBlack(zh)){
            throw new LoongyaException("用户账号为空");
        }
        return RE.ok(zh.getBalance());
    }
}
