package com.metoo.user.tj.service.impl;

import com.metoo.user.tj.dao.entity.TjUser;
import com.metoo.user.tj.dao.entity.TjUserInfo;
import com.metoo.user.tj.dao.mapper.TjUserMapper;
import com.metoo.user.tj.dao.repository.TjUserRepository;
import com.metoo.user.tj.service.TjUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public TjUser findByUsername(String username) {
        return tjUserRepository.findByUsername(username);
    }

    @Override
    public TjUser findByUid(Integer x) {
        return tjUserRepository.findByUid(x);
    }

    @Override
    public void updateUserPassword(String newPassword, String username) {
        tjUserRepository.updateUserPassword(newPassword,username);
    }
}
