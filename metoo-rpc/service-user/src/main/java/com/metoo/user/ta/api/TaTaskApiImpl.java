package com.metoo.user.ta.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.loongya.core.exception.LoongyaException;
import com.loongya.core.util.*;
import com.loongya.core.util.aliyun.OSSUtil;
import com.metoo.api.ta.TaTaskApi;
import com.metoo.api.tj.TjUserAccountDetailApi;
import com.metoo.pojo.old.vo.PublishTaskDTO;
import com.metoo.pojo.old.vo.TaskDTO;
import com.metoo.pojo.ta.model.MyTaTaskModel;
import com.metoo.pojo.ta.model.TaTaskImgModel;
import com.metoo.pojo.ta.model.TaTaskModel;
import com.metoo.pojo.ta.model.TaTaskUserModel;
import com.metoo.pojo.ta.vo.AppealTaskVo;
import com.metoo.pojo.ta.vo.CommitTaTaskVo;
import com.metoo.pojo.ta.vo.MyTaTaskVo;
import com.metoo.pojo.ta.vo.TaTaskVo;
import com.metoo.pojo.tj.model.TjUserAccountDetailAddDetailModel;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @Autowired
    private TjUserAccountDetailApi tjUserAccountDetailApi;


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
    public RE taskDetail(TaTaskVo vo) {
        TaTask pojo = taTaskService.getById(vo.getTaskId());
        if(OU.isBlack(pojo)){
            return RE.noData();
        }
        TaTaskModel model = CopyUtils.copy(pojo, new TaTaskModel());
        TjUserInfo userInfo = tjUserInfoService.findUserInfoByUserId(model.getUid());
        model.setNickName(userInfo.getNickName());
        model.setHeadImg(OSSUtil.fillPath(userInfo.getHeadImg()));
        model.setAge(userInfo.getAge());
        model.setSex(userInfo.getGender());
        model.setCity(userInfo.getProv()+userInfo.getCity());
        // 判断该任务与该用户的关系
        Long count = taTaskUserService.countByUidAndTaskId(vo.getUid(), model.getId());
        if(count>0){
            // getStatus ==0 : 可以领取 ==1:已经领取,不可以领取
            model.setGetStatus(1);
        }else {
            model.setGetStatus(0);
        }
        LambdaQueryWrapper<TaTaskImg> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TaTaskImg::getTaskId, model.getId());
        List<TaTaskImg> imgList = taTaskImgService.list(lqw);
        model.setImgModelList(imgList.stream().flatMap(e->{
            TaTaskImgModel imgModel = CopyUtils.copy(e, new TaTaskImgModel());
            imgModel.setImg(OSSUtil.fillPath(imgModel.getImg()));
            return Stream.of(imgModel);
        }).collect(Collectors.toList()));
        return RE.ok(model);
    }


    @Override
    public RE myAcceptTaskDetail(MyTaTaskVo vo) {
        TaTask pojo = taTaskService.getById(vo.getTaskId());
        if(OU.isBlack(pojo)){
            return RE.noData();
        }
        TaTaskModel model = CopyUtils.copy(pojo, new TaTaskModel());
        TjUserInfo userInfo = tjUserInfoService.findUserInfoByUserId(model.getUid());
        model.setNickName(userInfo.getNickName());
        model.setHeadImg(OSSUtil.fillPath(userInfo.getHeadImg()));
        model.setAge(userInfo.getAge());
        model.setSex(userInfo.getGender());
        model.setCity(userInfo.getProv()+userInfo.getCity());
        LambdaQueryWrapper<TaTaskImg> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TaTaskImg::getTaskId, model.getId());
        List<TaTaskImg> imgList = taTaskImgService.list(lqw);
        model.setImgModelList(imgList.stream().flatMap(e->{
            TaTaskImgModel imgModel = CopyUtils.copy(e, new TaTaskImgModel());
            imgModel.setImg(OSSUtil.fillPath(imgModel.getImg()));
            return Stream.of(imgModel);
        }).collect(Collectors.toList()));
        // 判断该任务与该用户的关系
        TaTaskUser taTaskUser = taTaskUserService.findFirstByUidAndTaskId(vo.getUid(), model.getId());
        if(OU.isBlack(taTaskUser))
            return RE.fail("业务出错");
        model.setTaskUserStatus(taTaskUser.getStatus());
        model.setTaskUserId(taTaskUser.getId());
        model.setTaskUserStatusName(ConstantUtil.TataskUserStatusEnum.getMsgByCode(taTaskUser.getStatus()));
        if(taTaskUser.getStatus() == ConstantUtil.TataskUserStatusEnum.PENDFAIL.getCode()){
            model.setAppealAnswer(taTaskUser.getAppealAnswer());
            model.setAppealRemark(taTaskUser.getAppealRemark());
            model.setAppealStatus(taTaskUser.getStatus());
        }
        return RE.ok(model);
    }
    @Override
    public RE myPublishTaskDetail(MyTaTaskVo vo) {
        TaTask pojo = taTaskService.getById(vo.getTaskId());
        if(OU.isBlack(pojo)){
            return RE.noData();
        }
        TaTaskModel model = CopyUtils.copy(pojo, new TaTaskModel());
        TjUserInfo userInfo = tjUserInfoService.findUserInfoByUserId(model.getUid());
        model.setNickName(userInfo.getNickName());
        model.setHeadImg(OSSUtil.fillPath(userInfo.getHeadImg()));
        model.setAge(userInfo.getAge());
        model.setSex(userInfo.getGender());
        model.setCity(userInfo.getProv()+userInfo.getCity());
        LambdaQueryWrapper<TaTaskImg> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TaTaskImg::getTaskId, model.getId());
        List<TaTaskImg> imgList = taTaskImgService.list(lqw);
        model.setImgModelList(imgList.stream().flatMap(e->{
            TaTaskImgModel imgModel = CopyUtils.copy(e, new TaTaskImgModel());
            imgModel.setImg(OSSUtil.fillPath(imgModel.getImg()));
            return Stream.of(imgModel);
        }).collect(Collectors.toList()));
        // 获取该任务 领取用户列表
        List<TaTaskUser> taTaskUserList = taTaskUserService.findAllByTaskId(model.getId());
        // 待完成列表
        List<TaTaskUserModel> goOnList = new ArrayList<>();
        // 待确认列表
        List<TaTaskUserModel> waitConfirmList = new ArrayList<>();
        // 已结束列表
        List<TaTaskUserModel> finishList = new ArrayList<>();
        if(OU.isNotBlack(taTaskUserList)){
            goOnList = taTaskUserList.stream().filter(e-> e.getStatus() == 1).flatMap(e->{
                TaTaskUserModel userModel = CopyUtils.copy(e, new TaTaskUserModel());
                handleTaskUser(userModel);

                return Stream.of(userModel);
            }).collect(Collectors.toList());

            waitConfirmList = taTaskUserList.stream().filter(e-> e.getStatus() == 2).flatMap(e->{
                TaTaskUserModel userModel = CopyUtils.copy(e, new TaTaskUserModel());
                handleTaskUser(userModel);
                return Stream.of(userModel);
            }).collect(Collectors.toList());

            finishList = taTaskUserList.stream().filter(e-> e.getStatus() == 3||e.getStatus() == 4||e.getStatus() == 5).flatMap(e->{
                TaTaskUserModel userModel = CopyUtils.copy(e, new TaTaskUserModel());
                handleTaskUser(userModel);
                return Stream.of(userModel);
            }).collect(Collectors.toList());
        }
        model.setGoOnList(goOnList);
        model.setWaitConfirmList(waitConfirmList);
        model.setFinishList(finishList);
        return RE.ok(model);
    }

    @Override
    public RE commitTask(CommitTaTaskVo vo) {
        // 提交任务 修改数据
        TaTaskUser pojo = taTaskUserService.getById(vo.getTaskUserId());
        if(OU.isBlack(pojo))
            return RE.fail(CommsEnum.BUSINESS_FAIL);
        if(pojo.getStatus() != ConstantUtil.TataskUserStatusEnum.TOTO.getCode()){
            return RE.fail("该任务状态不正确!");
        }
        TaTaskUser taTaskUser = new TaTaskUser();
        taTaskUser.setId(pojo.getId());
        taTaskUser.setImgs(vo.getImgs());
        taTaskUser.setRemark(vo.getRemark());
        taTaskUser.setStatus(ConstantUtil.TataskUserStatusEnum.PENDING.getCode());
        taTaskUserService.updateById(taTaskUser);
        return RE.ok();
    }

    /**
     *      * 提交任务后,发布者确认是通过还是拒绝
     *      * 如果通过,冻结的余额解冻,进入接收者的余额里面
     *      * 如果拒绝,冻结的余额七日后返回到发布者的余额
     *      * status ==3同意  或者 == 4 拒绝
     * @param vo
     * @return
     */
    @Override
    public RE confirmTask(MyTaTaskVo vo) {
        TaTaskUser pojo = taTaskUserService.getById(vo.getTataskUserId());
        assert pojo != null;
        if(pojo.getStatus() != ConstantUtil.TataskUserStatusEnum.PENDING.getCode()){
            return RE.fail("业务异常!");
        }
        // 修改状态
        TaTaskUser taTaskUser = new TaTaskUser();
        taTaskUser.setId(pojo.getId());
        taTaskUser.setStatus(vo.getStatus());
        if(vo.getStatus() == 4){
            taTaskUser.setRefuseRemark(vo.getRefuseRemark());
        }
        taTaskUserService.updateById(taTaskUser);
        if(vo.getStatus() == ConstantUtil.TataskUserStatusEnum.PENDED.getCode()){
            // 同意
            // 解冻余额, 接收者余额增加
            TaTask task = taTaskService.getById(pojo.getTaskId());
            assert task != null;
            TjUserAccount publishAccount = tjUserAccountService.findByUid(task.getUid());
            assert publishAccount != null;
            TjUserAccount userAccount = tjUserAccountService.findByUid(pojo.getUid());
            assert userAccount != null;
            tjUserAccountService.unFrozeenBalance(task.getUid(), task.getPrice());
            tjUserAccountService.updateBalanceUp(task.getPrice(), pojo.getUid());
            // todo. need asyn
            TjUserAccountDetailAddDetailModel acModel = new TjUserAccountDetailAddDetailModel();
            acModel.setUid(pojo.getUid());
            acModel.setRemark("做任务得到兔币");
            acModel.setContent("做任务,增加" + task.getPrice() + "兔币" + ", 任务id:{" + task.getId() + "}" + "任务标题: {" + task.getTitle() + "}");
            acModel.setPrice(task.getPrice());
            acModel.setAccountId(userAccount.getId());
            acModel.setType(ConstantUtil.TjUserAccountDetailTypeEnum.DOING_TASK_ADD_COIN.getCode());
            acModel.setTypeName(ConstantUtil.TjUserAccountDetailTypeEnum.DOING_TASK_ADD_COIN.getMsg());
            acModel.setAfterPrice(userAccount.getBalance().add(task.getPrice()));
            acModel.setPrePrice(userAccount.getBalance());
            tjUserAccountDetailApi.insertDetails(acModel);
        }else if (vo.getStatus() == ConstantUtil.TataskUserStatusEnum.PENDFAIL.getCode()){
            // todo. 加入延时队列,七日后返还余额
        }
        return RE.ok();
    }

    @Override
    public RE appealTask(AppealTaskVo vo) {
        TaTaskUser pojo = taTaskUserService.getById(vo.getTaTaskUserId());
        assert pojo != null;
        if(pojo.getStatus() != ConstantUtil.TataskUserStatusEnum.PENDFAIL.getCode()){
            return RE.fail("该订单状态正常,无法申诉");
        }
        TaTaskUser po = new TaTaskUser();
        po.setId(vo.getTaTaskUserId());
        po.setAppealRemark(vo.getAppealRemark());
        po.setAppealStatus(1);
        taTaskUserService.updateById(po);
        return RE.ok();
    }

    @Override
    public RE deleteTask(MyTaTaskVo vo) {
        Integer count = taTaskUserService.countByTaskId(vo.getTaskId());
        if(OU.isNotBlack(count)&& count >0){
            return RE.fail("无法删除,因为有用户接了此任务!");
        }
        taTaskService.removeById(vo.getTaskId());
        return RE.ok();
    }

    @Override
    public RE closeTask(MyTaTaskVo vo) {
        TaTask pojo = new TaTask();
        pojo.setId(vo.getTaskId());
        pojo.setState(ConstantUtil.CommStatusEnum.NO_NORMAL.getCode());
        taTaskService.updateById(pojo);
        return RE.ok(pojo);
    }


    private void handleTaskUser(TaTaskUserModel userModel) {
        TjUserInfo userInfo = tjUserInfoService.findUserInfoByUserId(userModel.getUid());
        userModel.setNickName(userInfo.getNickName());
        userModel.setHeadImg(OSSUtil.fillPath(userInfo.getHeadImg()));
    }

    /**
     * 任务列表
     * @param vo
     * @return
     */
    @Override
    public RE taskList(TaTaskVo vo) {
        TjUserInfo uinfo = tjUserInfoService.findUserInfoByUserId(vo.getUid());
        if(OU.isBlack(uinfo))
            return RE.fail("数据异常");
        vo.setLevels(uinfo.getLevel() + "");
        vo.setType(1);
        Integer pagenum = vo.getPagenum();
        vo.setPagenum((vo.getPagenum()-1)*vo.getPagesize());
        List<TaTaskModel> taskList = taTaskService.taskList(vo);
        if(OU.isBlack(taskList)){
            return RE.noData();
        }
        handleList(taskList, vo.getUid());
        return REPage.ok(pagenum, vo.getPagesize(), null, taskList);
    }

    /**
     * 我领取的任务列表
     * @param vo
     * @return
     */
    @Override
    public RE myAcceptTaskList(MyTaTaskVo vo) {
        // sql查询列表
        Integer pagenum = vo.getPagenum();
        vo.setPagenum((vo.getPagenum()-1)*vo.getPagesize());
        List<TaTaskModel> modelList = taTaskService.myAcceptTaskList(vo);
        if(OU.isBlack(modelList)){
            return RE.noData();
        }
        modelList.stream().forEach(e->{
            e.setHeadImg(OSSUtil.fillPath(e.getHeadImg()));
            // 我的任务做到了哪一步
            e.setTaskUserStatusName(ConstantUtil.TataskUserStatusEnum.getMsgByCode(e.getTaskUserStatus()));
        });
        // 封装列表
        return REPage.ok(pagenum, vo.getPagesize(), null, modelList);

    }

    /**
     * 我发布的任务
     * @param vo
     * @return
     */
    @Override
    public RE myPublishTaskList(MyTaTaskVo vo) {
        // sql查询列表
        Integer pagenum = vo.getPagenum();
        vo.setPagenum((vo.getPagenum()-1)*vo.getPagesize());
        List<TaTaskModel> modelList = taTaskService.myPublishTaskList(vo);
        if(OU.isBlack(modelList)){
            return RE.noData();
        }
        modelList.stream().forEach(e->{
            e.setHeadImg(OSSUtil.fillPath(e.getHeadImg()));
        });
        // 封装列表
        return REPage.ok(pagenum, vo.getPagesize(), null, modelList);
    }



    private void handleList(List<TaTaskModel> taskList, Integer uid) {
        taskList.stream().forEach(e->{
            TjUserInfo userInfo = tjUserInfoService.findUserInfoByUserId(e.getUid());
            e.setNickName(userInfo.getNickName());
            e.setHeadImg(OSSUtil.fillPath(userInfo.getHeadImg()));
            // 判断该任务与该用户的关系
            Long count = taTaskUserService.countByUidAndTaskId(uid, e.getId());
            if(count>0){
                // getStatus ==0 : 可以领取 ==1:已经领取,不可以领取
                e.setGetStatus(1);
            }else {
                e.setGetStatus(0);
            }
        });
    }

    /**
     * 教程任务列表
     * @param vo
     * @return
     */
    @Override
    public RE tutorialTaskList(TaTaskVo vo) {
        TjUserInfo uinfo = tjUserInfoService.findUserInfoByUserId(vo.getUid());
        if(OU.isBlack(uinfo))
            return RE.fail("数据异常");
        vo.setLevels(uinfo.getLevel() + "");
        vo.setType(2);
        Integer pagenum = vo.getPagenum();
        vo.setPagenum((vo.getPagenum()-1)*vo.getPagesize());
        List<TaTaskModel> taskList = taTaskService.taskList(vo);
        if(OU.isBlack(taskList)){
            return RE.noData();
        }
        handleList(taskList, vo.getUid());
        return REPage.ok(pagenum, vo.getPagesize(), null, taskList);
    }




    private void checkCanAccept(TaTask task, TaTaskVo vo) {
        // 任务数量必须> 实际接取的任务人数
        if(task.getPersonNum() <= taTaskUserService.countByTaskId(task.getId())){
            throw new LoongyaException("该任务已经被接完了!");
        }
        boolean levelFlag = true;
        boolean userFlag = true;
        // 判断用户是否有权限领取任务
        // 判断任务指定等级
        TjUserInfo userInfo = tjUserInfoService.findUserInfoByUserId(vo.getUid());
        if(OU.isBlack(userInfo))
            throw new LoongyaException(CommsEnum.BUSINESS_FAIL);
        if(task.getIsLevel() == ConstantUtil.YesOrNo.YES.getCode()){
            if(taTaskToLevelService.countAllByTaskIdAndLevel(task.getId(), userInfo.getLevel()) == 0){
                levelFlag = false;
            }
        }
        // 判断任务指定用户
        if(task.getIsUser() == ConstantUtil.YesOrNo.YES.getCode()){
            int userCount  = taTaskToUserService.countAllByTaskIdAndUserId(task.getId(), vo.getUid());
            if(userCount == 0){
               userFlag = false;
            }
        }
        if(levelFlag == false && userFlag == false){
            throw new LoongyaException("您没有权限接取该任务!");
        }
    }

    @Override
    public RE acceptTask(TaTaskVo vo) {
        TaTask task = taTaskService.getById(vo.getTaskId());
        if(task.getUid() == vo.getUid()){
            throw new LoongyaException("不能接受自己的任务");
        }

        checkCanAccept(task, vo);

        TjUserAccount account = tjUserAccountService.findByUid(vo.getUid());
        TjUserAccount publisherAccount = tjUserAccountService.findByUid(task.getUid());
        // 查询发布者余额是否足够
        if(publisherAccount.getBalance().compareTo(task.getPrice())<0){
            // todo. 此处设置通知 告知发布者先充值余额
            throw new LoongyaException("任务发布者余额不足,任务无法接受!");
        }
        BigDecimal payBalance = task.getPrice().multiply(new BigDecimal(0.1));
        // 查询接受者余额是否足够
        if(account.getBalance().compareTo(payBalance) < 0){
            throw new LoongyaException("您的余额不足,无法支付佣金,请充值!");
        }
        // 冻结发布者余额 冻结扣除的余额
        tjUserAccountService.frozeenBalance(task.getUid(), task.getPrice());
        tjUserAccountService.updateBalance(task.getPrice(), task.getUid());
        // todo. need asyn
        TjUserAccountDetailAddDetailModel acModel = new TjUserAccountDetailAddDetailModel();
        acModel.setUid(task.getUid());
        acModel.setRemark("任务发布支出");
        acModel.setContent("任务发布支出,支出" + task.getPrice() + "兔币" + ", 任务id:{" + task.getId() + "}" + "任务标题: {" + task.getTitle() + "}");
        acModel.setPrice(task.getPrice());
        acModel.setAccountId(publisherAccount.getId());
        acModel.setType(ConstantUtil.TjUserAccountDetailTypeEnum.TASK_ONE_ACCECT.getCode());
        acModel.setTypeName(ConstantUtil.TjUserAccountDetailTypeEnum.TASK_ONE_ACCECT.getMsg());
        acModel.setAfterPrice(publisherAccount.getBalance().subtract(task.getPrice()));
        acModel.setPrePrice(publisherAccount.getBalance());
        tjUserAccountDetailService.insertDetails(acModel);
        // 扣除任务接受者的余额
        tjUserAccountService.updateBalance(payBalance, vo.getUid());
        // todo. need asyn
        TjUserAccountDetailAddDetailModel detailModel = new TjUserAccountDetailAddDetailModel();
        detailModel.setUid(vo.getUid());
        detailModel.setRemark("接受任务支付佣金");
        detailModel.setContent("接受任务支付佣金,支出" + payBalance + "兔币" + ", 任务id:{" + task.getId() + "}" + "任务标题: {" + task.getTitle() + "}");
        detailModel.setPrice(payBalance);
        detailModel.setAccountId(account.getId());
        detailModel.setType(ConstantUtil.TjUserAccountDetailTypeEnum.ACCEPT_TASK_PAY_COMMISSION.getCode());
        detailModel.setTypeName(ConstantUtil.TjUserAccountDetailTypeEnum.ACCEPT_TASK_PAY_COMMISSION.getMsg());
        detailModel.setAfterPrice(account.getBalance().subtract(payBalance));
        detailModel.setPrePrice(account.getBalance());
        tjUserAccountDetailService.insertDetails(detailModel);
        // 新增任务用户表数据
        TaTaskUser pojo = new TaTaskUser();
        pojo.setUpdateTime(new Date());
        pojo.setCreateTime(new Date());
        pojo.setPrice(task.getPrice());
        pojo.setUid(vo.getUid());
        pojo.setTaskId(task.getId());
        pojo.setStatus(ConstantUtil.TataskUserStatusEnum.TOTO.getCode());
        taTaskUserService.save(pojo);
        return RE.ok("发布成功");
    }



}
