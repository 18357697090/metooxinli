package com.metoo.user.tj.service.impl;

import com.metoo.pojo.login.model.LoginModel;
import com.metoo.pojo.login.vo.LoginVo;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.pojo.tj.model.TjUserModel;
import com.metoo.user.tj.dao.entity.TjUser;
import com.metoo.user.tj.dao.entity.TjUserInfo;
import com.metoo.user.tj.dao.mapper.TjUserMapper;
import com.metoo.user.tj.dao.repository.TjUserRepository;
import com.metoo.user.tj.service.TjUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class TjUserServiceImpl extends ServiceImpl<TjUserMapper, TjUser> implements TjUserService {

    @Autowired
    private TjUserRepository tjUserRepository;

    @Resource
    private TjUserMapper tjUserMapper;

    @Autowired
    private Mapper mapper;


    @Override
    public TjUser findByUsername(String username) {
        return tjUserRepository.findByUsername(username);
    }

    @Override
    public LoginModel login(LoginVo vo) {
        return tjUserMapper.login(vo);
    }

    @Override
    public Integer findByExtendId(Integer extendId) {
        return tjUserMapper.findByExtendId(extendId);
    }
    @Override
    public TjUser findByOpenId(String openId) {
        return tjUserRepository.findFirstByOpenId(openId);
    }


}
