package com.metoo.web.controller.ta;


import com.loongya.core.util.RE;
import com.metoo.api.ta.TaTaskApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 任务大厅表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ta/taTask")
public class TaTaskController {

    @DubboReference
    private TaTaskApi taTaskApi;

    //任务大厅
    @GetMapping("/taskList")
    public RE taskList(Integer page) {
        return taTaskApi.taskList(page);

    }

    //教程任务任务大厅
    @GetMapping("/tutorialTaskList")
    public List<TaskDTO> tutorialTaskList(Integer page) {
        Pageable pageable = PageRequest.of(page, 7, Sort.Direction.DESC, "taskPrices");
        List<TaskDTO> TaskDTOs = new ArrayList<>();
        List<Task> tasks = taskDao.findTutorialTask(pageable);
        for (Task task : tasks) {
            TaskDTO taskDTO = mapper.map(task, TaskDTO.class);
            int uid = task.getUid();
            UserInfo userInfo = userInfoDao.findByUid(uid);
            taskDTO.setName(userInfo.getName());
            taskDTO.setPicture(userInfo.getPicture());
            TaskDTOs.add(taskDTO);
        }
        return TaskDTOs;
    }

    //发布任务
    @PostMapping("/publishTask")
    public ReturnMessage publishTask(@RequestBody PublishTaskDTO publishTaskDTO, @RequestHeader("UID")Integer uid){
        ReturnMessage returnMessage = new ReturnMessage();
        System.out.println(publishTaskDTO.getPrices());
        returnMessage.setState("error");
        Task task = mapper.map(publishTaskDTO,Task.class);
        task.setTaskPrices(publishTaskDTO.getPrices());
        task.setUid(uid);
        Zh zh = zhDao.findByUid(uid);
        int x = zh.getBalance().compareTo(task.getTaskPrices());
        if(x<0){
            returnMessage.setExplain("你的余额不足");
            return returnMessage;
        }else {
            BigDecimal balance = zh.getBalance().subtract(task.getTaskPrices());
            zhDao.updateBalance(balance,uid);
        }
        int taskId = createID.create();
        Task task1 = taskDao.findByTaskId(taskId);
        while (task1!=null){
            taskId=createID.create();
            task1 = taskDao.findByTaskId(taskId);
        }
        task.setTaskId(taskId);
        taskDao.save(task);
        //消费记录
        ZhRecord zhRecord = new ZhRecord();
        zhRecord.setUid(uid);
        zhRecord.setPrices(task.getTaskPrices());
        zhRecord.setType("发布任务");
        zhRecordDao.save(zhRecord);
        //我的任务
        UserTask userTask = new UserTask();
        userTask.setPublishId(uid);
        userTask.setTaskId(taskId);
        userTask.setType(1);
        userTask.setState(1);
        userTaskDao.save(userTask);
        returnMessage.setState("success");
        returnMessage.setExplain("发布成功");
        return returnMessage;
    }

    //接受任务
    @GetMapping("/acceptTask")
    public ReturnMessage acceptTask(@RequestHeader("UID")Integer uid,Integer taskId){
        ReturnMessage returnMessage = new ReturnMessage();
        returnMessage.setState("error");
        Task task = taskDao.findByTaskId(taskId);
        if(task.getUid().equals(uid)){
            returnMessage.setExplain("自己的任务，不能接受");
            return returnMessage;
        }
        Zh zh = zhDao.findByUid(uid);
        BigDecimal bigDecimal = new BigDecimal("10");
        BigDecimal y = task.getTaskPrices().divide(bigDecimal,2);
        int x = zh.getBalance().compareTo(y);
        if(x<0){
            returnMessage.setExplain("余额不足");
            return returnMessage;
        }else {
            BigDecimal ye = zh.getBalance().subtract(y);
            zhDao.updateBalance(ye,uid);
            taskDao.updateTaskState(taskId);
            userTaskDao.updateAcceptId(uid,taskId);
            ZhRecord zhRecord = new ZhRecord();
            zhRecord.setPrices(y);
            zhRecord.setUid(uid);
            zhRecord.setType("接受任务");
            zhRecordDao.save(zhRecord);
            returnMessage.setState("success");
            returnMessage.setExplain("发布成功");
            return returnMessage;
        }
    }

    //发布人提交完成任务
//    @GetMapping("/publishSubmitTask")
//    public ReturnMessage publishSubmitTask(@RequestHeader("UID")Integer uid,Integer taskId){
//        ReturnMessage returnMessage = new ReturnMessage();
//        returnMessage.setState("error");
//        UserTask userTask = userTaskDao.findByTaskId(taskId);
//        if(userTask.getAcceptState()!=1){
//
//        }
//
//         if(userTaskDao.updatePublishState(taskId,uid)==1){
//
//             return "success";
//         }else {
//             return "error";
//         }
//    }

    //接收人提交完成任务
    @GetMapping("/acceptSubmitTask")
    public String acceptSubmitTask(@RequestHeader("UID")Integer uid,Integer taskId) {

        if(userTaskDao.updateAcceptState(taskId,uid)==1){
            return "success";
        }else {
            return "error";
        }
    }


    //我的任务
//    @GetMapping("myTask")
//    public List<> myTask(@RequestHeader("UID")Integer uid){
//        my
//    }



    //删除任务
//    @GetMapping("/deleteTask")
//    public String deleteTask(@RequestHeader("UID")Integer uid,Integer taskId){
//
//    }


}
