package com.metoo.user.ta.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ta.TaTaskApi;
import com.metoo.pojo.old.vo.PublishTaskDTO;
import com.metoo.pojo.old.vo.TaskDTO;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.tools.CreateID;
import com.metoo.user.ta.dao.entity.TaTask;
import com.metoo.user.ta.dao.entity.TaTaskUser;
import com.metoo.user.ta.service.TaTaskService;
import com.metoo.user.ta.service.TaTaskUserService;
import com.metoo.user.tj.dao.entity.TjUserAccount;
import com.metoo.user.tj.dao.entity.TjUserAccountDetail;
import com.metoo.user.tj.service.TjUserAccountDetailService;
import com.metoo.user.tj.service.TjUserAccountService;
import com.metoo.user.tj.service.TjUserInfoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
@Component
@DubboService
@Transactional
public class TaTaskApiImpl implements TaTaskApi {

    @Autowired
    private TaTaskService taTaskService;

    @Autowired
    private TjUserInfoService tjUserInfoService;

    @Autowired
    private TjUserAccountService tjUserAccountService;

    @Autowired
    private TjUserAccountDetailService tjUserAccountDetailService;

    @Autowired
    private TaTaskUserService taTaskUserService;

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
            TjUserInfoModel userInfo = tjUserInfoService.findByUid(uid);
            taskDTO.setName(userInfo.getNickName());
            taskDTO.setPicture(userInfo.getHeadImg());
            TaskDTOs.add(taskDTO);
        }
        if(OU.isBlack(TaskDTOs)){
            return RE.noData();
        }
        return RE.ok(TaskDTOs);
    }

    @Override
    public RE tutorialTaskList(Integer page) {
        Pageable pageable = PageRequest.of(page, 7, Sort.Direction.DESC, "taskPrices");
        List<TaskDTO> TaskDTOs = new ArrayList<>();
        List<TaTask> tasks = taTaskService.findTutorialTask(pageable);
        for (TaTask task : tasks) {
            TaskDTO taskDTO = mapper.map(task, TaskDTO.class);
            int uid = task.getUid();
            TjUserInfoModel userInfo = tjUserInfoService.findByUid(uid);
            taskDTO.setName(userInfo.getNickName());
            taskDTO.setPicture(userInfo.getHeadImg());
            TaskDTOs.add(taskDTO);
        }
        if(OU.isBlack(TaskDTOs)){
            return RE.noData();
        }
        return RE.ok(TaskDTOs);
    }

    @Override
    public RE publishTask(PublishTaskDTO publishTaskDTO, Integer uid) {

        TaTask task = mapper.map(publishTaskDTO,TaTask.class);
        task.setPrice(publishTaskDTO.getPrices());
        task.setUid(uid);
        TjUserAccount zh = tjUserAccountService.findByUid(uid);
        int x = zh.getBalance().compareTo(task.getPrice());
        if(x<0){
            return RE.fail("你的余额不足");
        }else {
            BigDecimal balance = zh.getBalance().subtract(task.getPrice());
            tjUserAccountService.updateBalance(task.getPrice(),uid);
        }
        taTaskService.save(task);
        //消费记录
        TjUserAccountDetail zhRecord = new TjUserAccountDetail();
        zhRecord.setUid(uid);
        zhRecord.setPrice(task.getPrice());
        zhRecord.setRemark("发布任务");
        tjUserAccountDetailService.save(zhRecord);
        //我的任务
        TaTaskUser userTask = new TaTaskUser();
        userTask.setUid(uid);
        userTask.setTaskId(task.getId());
        userTask.setStatus(1);
        taTaskUserService.save(userTask);
        return RE.ok();
    }

    @Override
    public RE acceptTask(Integer uid, Integer taskId) {
        TaTask task = taTaskService.findByTaskId(taskId);
        if(task.getUid().equals(uid)){
            return RE.fail("自己的任务，不能接受");
        }
        TjUserAccount zh = tjUserAccountService.findByUid(uid);
        BigDecimal bigDecimal = new BigDecimal("10");
        BigDecimal y = task.getPrice().divide(bigDecimal,2);
        int x = zh.getBalance().compareTo(y);
        if(x<0){
            return RE.fail("余额不足");
        }else {
            BigDecimal ye = zh.getBalance().subtract(y);
            tjUserAccountService.updateBalance(y,uid);
            taTaskService.updateTaskState(taskId);
            taTaskUserService.updateAcceptId(uid,taskId);
            TjUserAccountDetail zhRecord = new TjUserAccountDetail();
            zhRecord.setPrice(y);
            zhRecord.setUid(uid);
            zhRecord.setRemark("接受任务");
            tjUserAccountDetailService.save(zhRecord);
            return RE.ok("发布成功");
        }
    }

    @Override
    public RE acceptSubmitTask(Integer uid, Integer taskId) {

        if(taTaskUserService.updateAcceptState(taskId,uid)==1){
            return RE.ok();
        }else {
            return RE.fail("error");
        }
    }
}
