package com.metoo.user.ta.service.impl;

import com.metoo.user.ta.dao.entity.TaTaskUser;
import com.metoo.user.ta.dao.mapper.TaTaskUserMapper;
import com.metoo.user.ta.dao.repository.TaTaskUserRepository;
import com.metoo.user.ta.service.TaTaskUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户我的任务表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-29
 */
@Service
public class TaTaskUserServiceImpl extends ServiceImpl<TaTaskUserMapper, TaTaskUser> implements TaTaskUserService {

    @Autowired
    private TaTaskUserRepository taTaskUserRepository;

    @Override
    public void updateAcceptId(Integer uid, Integer taskId) {
        taTaskUserRepository.updateAcceptId(uid, taskId);
    }

    @Override
    public int updateAcceptState(Integer taskId, Integer uid) {
        return taTaskUserRepository.updateAcceptState(taskId, uid);
    }
}
