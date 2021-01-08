package com.metoo.user.ta.api;

import com.loongya.core.util.ConstantUtil;
import com.loongya.core.util.CopyUtils;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.loongya.core.util.aliyun.OSSUtil;
import com.metoo.api.ta.TaTaskApi;
import com.metoo.pojo.old.vo.PublishTaskDTO;
import com.metoo.pojo.old.vo.TaskDTO;
import com.metoo.pojo.ta.model.TaTaskModel;
import com.metoo.pojo.ta.vo.TaTaskVo;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.tools.CreateID;
import com.metoo.user.ta.dao.entity.*;
import com.metoo.user.ta.service.*;
import com.metoo.user.tj.dao.entity.TjUserAccount;
import com.metoo.user.tj.dao.entity.TjUserAccountDetail;
import com.metoo.user.tj.dao.entity.TjUserInfo;
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
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    private TaTaskImgService taTaskImgService;

    @Autowired
    private TaTaskToLevelService taTaskToLevelService;

    @Autowired
    private TaTaskToUserService taTaskToUserService;

    /**
     * 发布任务
     * @param vo
     * @return
     */
    @Override
    public RE publishTask(TaTaskVo vo) {
        TaTask po = new TaTask();
        po.setUpdateTime(new Date());
        po.setUid(vo.getUid());
        po.setType(vo.getType());
        po.setTitle(vo.getTitle());
        po.setState(ConstantUtil.CommStatusEnum.NORMAL.getCode());
        po.setStartTime(vo.getStartTime());
        po.setEndTime(vo.getEndTime());
        po.setPersonNum(vo.getPersonNum());
        po.setCreateTime(new Date());
        po.setContent(vo.getContent());
        po.setPrice(vo.getPrice());
        if(OU.isNotBlack(vo.getLevels()))
            po.setIsLevel(0);
        if(OU.isNotBlack(vo.getUserIds()))
            po.setIsUser(0);
        taTaskService.save(po);
        if(OU.isNotBlack(vo.getImgs())){
            List<String> imgList = new ArrayList<>(Arrays.asList(vo.getImgs().split(",")));
            imgList.stream().forEach(e->{
                TaTaskImg imgpo = new TaTaskImg();
                imgpo.setTaskId(po.getId());
                imgpo.setImg(e);
                taTaskImgService.save(imgpo);
            });
        }
        if(OU.isNotBlack(vo.getLevels())){
            List<String> levelList = new ArrayList<>(Arrays.asList(vo.getLevels().split(",")));
            levelList.stream().forEach(e->{
                TaTaskToLevel lepo = new TaTaskToLevel();
                lepo.setTaskId(po.getId());
                lepo.setLevel(Integer.parseInt(e));
                taTaskToLevelService.save(lepo);
            });
        }
        if(OU.isNotBlack(vo.getUserIds())){
            List<String> userIdList = new ArrayList<>(Arrays.asList(vo.getUserIds().split(",")));
            userIdList.stream().forEach(e->{
                TaTaskToUser taskToUserPo = new TaTaskToUser();
                taskToUserPo.setTaskId(po.getId());
                taskToUserPo.setUserId(Integer.parseInt(e));
                taTaskToUserService.save(taskToUserPo);
            });
        }
        return RE.ok();
    }




    @Override
    public RE taskList(TaTaskVo vo) {
        TjUserInfo uinfo = tjUserInfoService.findUserInfoByUserId(vo.getUid());
        Assert.isNull(uinfo, "数据异常");
        vo.setLevels(uinfo.getLevel() + "");
        List<TaTaskModel> taskList = taTaskService.taskList(vo);
        if(OU.isBlack(taskList)){
            return RE.noData();
        }
        taskList.stream().forEach(e->{
            TjUserInfo userInfo = tjUserInfoService.findUserInfoByUserId(e.getUid());
            e.setNickName(userInfo.getNickName());
            e.setHeadImg(OSSUtil.fillPath(userInfo.getHeadImg()));
            // 判断该任务与该用户的关系
            Long count = taTaskUserService.countByUidAndTaskId(vo.getUid(), e.getId());
            if(count>0){
                // getStatus ==0 : 可以领取 ==1:已经领取,不可以领取
                e.setGetStatus(1);
            }else {
                e.setGetStatus(0);
            }
        });
        return RE.ok(taskList);
    }

    @Override
    public RE tutorialTaskList(Integer page) {
        Pageable pageable = PageRequest.of(page, 7, Sort.Direction.DESC, "taskPrices");
        List<TaskDTO> TaskDTOs = new ArrayList<>();
        List<TaTask> tasks = taTaskService.findTutorialTask(pageable);
        for (TaTask task : tasks) {
            TaskDTO taskDTO = CopyUtils.copy(task, new TaskDTO());
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
