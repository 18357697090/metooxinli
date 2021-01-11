package com.metoo.user.ta.service.impl;

import com.metoo.user.ta.dao.entity.TaTaskUser;
import com.metoo.user.ta.dao.mapper.TaTaskUserMapper;
import com.metoo.user.ta.dao.repository.TaTaskUserRepository;
import com.metoo.user.ta.service.TaTaskUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Long countByUidAndTaskId(Integer uid, Integer id) {
        return taTaskUserRepository.countAllByUidAndTaskId(uid, id);
    }

    @Override
    public TaTaskUser findFirstByUidAndTaskId(Integer uid, Integer id) {
        return taTaskUserRepository.findFirstByUidAndTaskId(uid, id);
    }

    @Override
    public List<TaTaskUser> findAllByTaskId(Integer taskId) {
        return taTaskUserRepository.findAllByTaskId(taskId);
    }

    @Override
    public Integer countByTaskId(Integer taskId) {
        return taTaskUserRepository.countByTaskId(taskId);
    }
}
