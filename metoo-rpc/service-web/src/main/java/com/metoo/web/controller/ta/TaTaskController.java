package com.metoo.web.controller.ta;


import com.loongya.core.util.AssertUtils;
import com.loongya.core.util.RE;
import com.metoo.api.ta.TaTaskApi;
import com.metoo.pojo.old.vo.PublishTaskDTO;
import com.metoo.pojo.ta.vo.TaTaskVo;
import com.metoo.web.config.auth.ThreadLocal;
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


    /**
     * ok
     *发布任务
     */
    @PostMapping("/publishTask")
    public RE publishTask(TaTaskVo vo){
        AssertUtils.checkParam(vo.getTitle(), vo.getContent(), vo.getPrice(), vo.getType());
        vo.setUid(ThreadLocal.getUserId());
        return taTaskApi.publishTask(vo);
    }


    /**
     * 任务大厅任务列表
     * @return
     */
    @GetMapping("/taskList")
    public RE taskList(TaTaskVo vo) {
        vo.setUid(ThreadLocal.getUserId());
        return taTaskApi.taskList(vo);

    }

    //教程任务任务大厅
    @GetMapping("/tutorialTaskList")
    public RE tutorialTaskList(Integer page) {
        return taTaskApi.tutorialTaskList(page);
    }


    //接受任务
    @GetMapping("/acceptTask")
    public RE acceptTask(@RequestHeader("UID")Integer uid,Integer taskId){
        return taTaskApi.acceptTask(uid, taskId);

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
    public RE acceptSubmitTask(@RequestHeader("UID")Integer uid,Integer taskId) {
        return taTaskApi.acceptSubmitTask(uid, taskId);

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
