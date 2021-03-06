package com.metoo.user.tj.service.impl;

import com.loongya.core.exception.LoongyaException;
import com.loongya.core.util.OU;
import com.loongya.core.util.aliyun.OSSUtil;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.user.tj.dao.entity.TjUserInfo;
import com.metoo.user.tj.dao.mapper.TjUserInfoMapper;
import com.metoo.user.tj.dao.repository.TjUserInfoRepository;
import com.metoo.user.tj.service.TjUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户个人信息表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class TjUserInfoServiceImpl extends ServiceImpl<TjUserInfoMapper, TjUserInfo> implements TjUserInfoService {

    @Autowired
    private TjUserInfoRepository tjUserInfoRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public TjUserInfoModel findByUid(Integer uid) {
        TjUserInfo pojo = tjUserInfoRepository.findByUid(uid);
        if(OU.isBlack(pojo)){
            throw new LoongyaException("没有该用户信息");
        }
        TjUserInfoModel model = mapper.map(pojo, TjUserInfoModel.class);
        model.setHeadImg(OSSUtil.fillPath(model.getHeadImg()));
        return model;
    }

    @Override
    public TjUserInfo findUserInfoByUserId(Integer userId) {
        return tjUserInfoRepository.findByUid(userId);
    }

    @Override
    public int updateUserInfo(String name, String picture, String city, String motto, Integer uid) {
        return tjUserInfoRepository.updateUserInfo(name, picture, city, motto, uid);
    }

    @Override
    public List<TjUserInfo> findByNameLike(String name) {
        return tjUserInfoRepository.findByNickNameLike("%" + name + "%");
    }
}
