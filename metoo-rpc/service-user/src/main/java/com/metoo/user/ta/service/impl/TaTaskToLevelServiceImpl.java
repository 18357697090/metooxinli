package com.metoo.user.ta.service.impl;

import com.metoo.user.ta.dao.entity.TaTaskToLevel;
import com.metoo.user.ta.dao.mapper.TaTaskToLevelMapper;
import com.metoo.user.ta.dao.repository.TaTaskToLevelRepository;
import com.metoo.user.ta.service.TaTaskToLevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务指定等级 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2021-01-08
 */
@Service
public class TaTaskToLevelServiceImpl extends ServiceImpl<TaTaskToLevelMapper, TaTaskToLevel> implements TaTaskToLevelService {

    @Autowired
    private TaTaskToLevelRepository taTaskToLevelRepository;

    @Override
    public int countAllByTaskIdAndLevel(Integer taskId, Integer level) {
        return taTaskToLevelRepository.countAllByTaskIdAndLevelGreaterThanEqual(taskId, level);
    }
}
