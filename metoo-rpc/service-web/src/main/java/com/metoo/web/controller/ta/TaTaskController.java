package com.metoo.web.controller.ta;


import com.loongya.core.util.AssertUtils;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ta.TaTaskApi;
import com.metoo.pojo.old.vo.PublishTaskDTO;
import com.metoo.pojo.ta.model.MyTaTaskModel;
import com.metoo.pojo.ta.model.TaTaskModel;
import com.metoo.pojo.ta.vo.AppealTaskVo;
import com.metoo.pojo.ta.vo.CommitTaTaskVo;
import com.metoo.pojo.ta.vo.MyTaTaskVo;
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

    /**
     * 教程任务任务大厅
     * @param vo
     * @return
     */
    @GetMapping("/tutorialTaskList")
    public RE tutorialTaskList(TaTaskVo vo) {
        vo.setUid(ThreadLocal.getUserId());
        return taTaskApi.tutorialTaskList(vo);
    }


    /**
     * 任务详情
     * @param vo
     * @return
     */
    @GetMapping("/taskDetail")
    public RE taskDetail(TaTaskVo vo) {
        AssertUtils.checkParam(vo.getTaskId());
        vo.setUid(ThreadLocal.getUserId());
        return taTaskApi.taskDetail(vo);
    }


    /**
     * 接受任务
     * @return
     */
    @GetMapping("/acceptTask")
    public RE acceptTask(TaTaskVo vo){
        AssertUtils.checkParam(vo.getTaskId());
        vo.setUid(ThreadLocal.getUserId());
        return taTaskApi.acceptTask(vo);
    }


    /**
     * 我接受的任务列表
     */
    @GetMapping("myAcceptTaskList")
    public RE myAcceptTaskList(MyTaTaskVo vo){
        vo.setUid(ThreadLocal.getUserId());
        return taTaskApi.myAcceptTaskList(vo);
    }

    /**
     * 我发布的任务列表
     */
    @GetMapping("myPublishTaskList")
    public RE myPublishTaskList(MyTaTaskVo vo){
        vo.setUid(ThreadLocal.getUserId());
        return taTaskApi.myPublishTaskList(vo);
    }

    /**
     * 我发布的任务任务详情
     */
    @GetMapping("/myPublishTaskDetail")
    public RE myPublishTaskDetail(MyTaTaskVo vo){
        AssertUtils.checkParam(vo.getTaskId());
        vo.setUid(ThreadLocal.getUserId());
        return taTaskApi.myPublishTaskDetail(vo);
    }
    /**
     * 我领取的任务任务详情
     */
    @GetMapping("/myAcceptTaskDetail")
    public RE myAcceptTaskDetail(MyTaTaskVo vo){
        AssertUtils.checkParam(vo.getTaskId());
        vo.setUid(ThreadLocal.getUserId());
        return taTaskApi.myAcceptTaskDetail(vo);
    }

    /**
     * ok
     * 领取任务后,提交任务接口
     * taskUserId: 不是用户id,是ta_task_user表的id
     */
    @GetMapping("commitTask")
    public RE commitTask(CommitTaTaskVo vo){
        AssertUtils.checkParam(vo.getTaskUserId());
        vo.setUid(ThreadLocal.getUserId());
        return taTaskApi.commitTask(vo);
    }
    /**
     * 提交任务后,发布者确认是通过还是拒绝
     * 如果通过,冻结的余额解冻,进入接收者的余额里面
     * 如果拒绝,冻结的余额七日后返回到发布者的余额
     * status ==3同意  或者 == 4 拒绝
     * refuseRemark : 拒绝理由
     *
     */
    @GetMapping("confirmTask")
    public RE confirmTask(MyTaTaskVo vo){
        AssertUtils.checkParam(vo.getTataskUserId(), vo.getStatus());
        assert vo.getStatus() == 3 || vo.getStatus() == 4;
        assert vo.getStatus() == 4 && OU.isNotBlack(vo.getRefuseRemark());
        vo.setUid(ThreadLocal.getUserId());
        return taTaskApi.confirmTask(vo);
    }

    /**
     * 申诉接口 ,用户完成任务后,被发布者拒绝,可在这个接口申诉,由平台解决,申诉时间为7天内.
     */
    @GetMapping("appealTask")
    public RE appealTask(AppealTaskVo vo){
        AssertUtils.checkParam(vo.getTaTaskUserId());
        vo.setUid(ThreadLocal.getUserId());
        return taTaskApi.appealTask(vo);
    }


    /**
     * 删除任务 有如下情形不能删除:
     *  1: 任务有人接取
     *
     * @return
     */
    @GetMapping("/deleteTask")
    public RE deleteTask(MyTaTaskVo vo){
        AssertUtils.checkParam(vo.getTaskId());
        vo.setUid(ThreadLocal.getUserId());
        return taTaskApi.deleteTask(vo);
    }

    /**
     * 关闭任务
     *
     * @return
     */
    @GetMapping("/closeTask")
    public RE closeTask(MyTaTaskVo vo){
        AssertUtils.checkParam(vo.getTaskId());
        return taTaskApi.closeTask(vo);
    }


}
