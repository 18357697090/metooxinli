package com.metoo.user.ta.service.impl;

import com.metoo.user.ta.dao.entity.TaTask;
import com.metoo.user.ta.dao.mapper.TaTaskMapper;
import com.metoo.user.ta.dao.repository.TaTaskRepository;
import com.metoo.user.ta.service.TaTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 任务大厅表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class TaTaskServiceImpl extends ServiceImpl<TaTaskMapper, TaTask> implements TaTaskService {

    @Autowired
    private TaTaskRepository taTaskRepository;

    @Override
    public List<TaTask> findOrdinaryTask(Pageable pageable) {
        return taTaskRepository.findOrdinaryTask(pageable);
    }
}
