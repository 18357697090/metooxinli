package com.metoo.user.tj.service.impl;

import com.metoo.user.tj.dao.entity.TjSecretGuard;
import com.metoo.user.tj.dao.mapper.TjSecretGuardMapper;
import com.metoo.user.tj.dao.repository.TjSecretGuardRepository;
import com.metoo.user.tj.service.TjSecretGuardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户密保问题 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class TjSecretGuardServiceImpl extends ServiceImpl<TjSecretGuardMapper, TjSecretGuard> implements TjSecretGuardService {

    @Autowired
    private TjSecretGuardRepository tjSecretGuardRepository;

    @Override
    public TjSecretGuard findByUsername(String username) {
        return tjSecretGuardRepository.findByUsername(username);
    }
}
