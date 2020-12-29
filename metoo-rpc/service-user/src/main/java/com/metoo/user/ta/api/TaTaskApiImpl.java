package com.metoo.user.ta.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ta.TaTaskApi;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.pojo.old.vo.TaskDTO;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.user.ta.dao.entity.TaTask;
import com.metoo.user.ta.service.TaTaskService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 任务大厅表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public class TaTaskApiImpl implements TaTaskApi {

    @Autowired
    private TaTaskService taTaskService;

    @DubboReference
    private TjUserInfoApi tjUserInfoApi;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public RE taskList(Integer page) {
        Pageable pageable = PageRequest.of(page, 7, Sort.Direction.DESC, "taskPrices");
        List<TaskDTO> TaskDTOs = new ArrayList();
        List<TaTask> tasks = taTaskService.findOrdinaryTask(pageable);
        for (TaTask task : tasks) {
            TaskDTO taskDTO = mapper.map(task, TaskDTO.class);
            int uid = task.getUid();
            TjUserInfoModel userInfo = tjUserInfoApi.findByUid(uid);
            taskDTO.setName(userInfo.getName());
            taskDTO.setPicture(userInfo.getPicture());
            TaskDTOs.add(taskDTO);
        }
        if(OU.isBlack(TaskDTOs)){
            return RE.noData();
        }
        return RE.ok(TaskDTOs);
    }
}
