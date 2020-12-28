package com.metoo.web.controller.backup;

import com.metoo.metoo.entity.Comment;
import com.metoo.metoo.entity.UserInfo;
import com.metoo.metoo.entity.Zh;
import com.metoo.metoo.pojo.Comments;
import com.metoo.metoo.pojo.Problems;
import com.metoo.metoo.pojo.Result;
import com.metoo.metoo.pojo.ScaleDetails;
import com.metoo.metoo.psychology.*;
import com.metoo.metoo.psychologyDao.*;
import com.metoo.metoo.repository.*;
import com.metoo.metoo.tools.CalculateTheScore;
import com.metoo.metoo.tools.CommentsTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/measure")
@Api(tags={"心理测量相关接口"})
public class PsychologyMeasureHandler {
    @Autowired
    private ScaleDao scaleDao;
    @Autowired
    private ProblemDao problemDao;
    @Autowired
    private UserAndMeasureDao userAndMeasureDao;
    @Autowired
    private ZhDao zhDao;
    @Autowired
    private ScaleGatherDao scaleGatherDao;
    @Autowired
    private OptionsDao optionsDao;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private ScaleDetailDao scaleDetailDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private LevelPictureDao levelPictureDao;



    @GetMapping("/clgather")
    @ApiOperation("推荐测量")
    public List<ScaleGather> clgather(int uid){
        UserInfo userInfo = userInfoDao.findByUid(uid);
        int dw=1;
        if(userInfo !=null){
            dw= userInfo.getDw();
        }
        List<ScaleGather> scaleGathers =new ArrayList<>();
        scaleGathers.add(scaleGatherDao.findByScaleGatherId(dw));
        scaleGathers.add(scaleGatherDao.findByScaleGatherId(101));
        scaleGathers.add(scaleGatherDao.findByScaleGatherId(102));
        return scaleGathers;
    }

    @ApiOperation("精品测量")
    @GetMapping("/cl")
    public List<Scale> cl(Integer page){
        Pageable pageable= PageRequest.of(page,7);
        return scaleDao.findAllBySpare(1,pageable);
    }

    //测量详情
    @ApiOperation("测量详情")
    @GetMapping("/gaugedetails")
    public ScaleDetails gaugedetails(Integer scaleId,@RequestHeader("UID") Integer uid){
        ScaleDetails scaleDetails =new ScaleDetails();
        Scale scale=scaleDao.findByScaleId(scaleId);
        if(scale==null){
            scaleDetails.setState("error");
            return scaleDetails;
        }
        scaleDetails.setTitle(scale.getName());
        scaleDetails.setContent(scale.getContent());
        scaleDetails.setNumberOfProblem(scale.getNumber());
        ScaleDetail scaleDetail= scaleDetailDao.findByScaleId(scaleId);
        if(scaleDetail==null){
            scaleDetails.setState("error");
            return scaleDetails;
        }
        scaleDetails.setTopPicture(scaleDetail.getTopPicture());
        scaleDetails.setScaleNeedToKnow(scaleDetail.getScaleNeedToKnow());
        scaleDetails.setContentPicture(scaleDetail.getContentPicture());
        scaleDetails.setPrice(scale.getPrices());
        scaleDetails.setNumberOfProblem(scale.getNumberOfProblem());
        Pageable pageable=PageRequest.of(0,3);
        List<Comment> comments=  commentDao.findByScaleId(scaleId,pageable);
        if (comments==null){
            scaleDetails.setState("error");
            return scaleDetails;
        }
        List<Comments> comments1 = new ArrayList<>();
        for (Comment a : comments ) {
            Comments comments2=new Comments();
            UserInfo userInfo = userInfoDao.findByUid(a.getUid());
            comments2.setComment(a.getComment());
            comments2.setUsername(userInfo.getName());
            comments2.setUserPicture(userInfo.getPicture());
            comments2.setLevelPicture(levelPictureDao.findByLevel(userInfo.getDw()).getLevelPicture());
            comments2.setCreateTime(a.getCreateTime());
            comments1.add(comments2);
        }
        scaleDetails.setComments(comments1);
        UserAndMeasure userAndMeasure= userAndMeasureDao.findByUidAndScaleId(uid,scaleId);
        Zh zh=zhDao.findByUid(uid);
        scaleDetails.setAccountBalance(zh.getBalance());
        if(userAndMeasure==null){
            scaleDetails.setState("success");
            return scaleDetails;
        }else if(userAndMeasure.getState()==1){
            scaleDetails.setState("exist");
            return scaleDetails;//表示该玩家付费但没做完
        }else if(userAndMeasure.getState()==2){
            scaleDetails.setState("done");
            return scaleDetails;//表示玩家已经做过了
        }else {
            scaleDetails.setState("success");
            return scaleDetails;
        }
    }

    //所有测量集合
    @GetMapping("/clgatherall")
    @ApiOperation("更多推荐测量")
    public List<ScaleGather> clgaherall(){
        return scaleGatherDao.findByScaleGatherIdAll();
    }

    @GetMapping("/findbyclgatherid")
    @ApiOperation("推荐测量集合内容")
    public List<Scale> findbyclgatherid(@ApiParam("测量集合的id")Integer clgatherid){
        return scaleDao.findByScaleGatherId(clgatherid);
    }

    //支付
    @ApiOperation("支付")
    @GetMapping("/pay")
    public String pay(Integer uid,Integer scaleId){
        Scale scale = scaleDao.findByScaleId(scaleId);
        Zh zh=zhDao.findByUid(uid);
        int x =zh.getBalance().compareTo(scale.getPrices());
        if(x >= 0) {
            BigDecimal balance = zh.getBalance().subtract(scale.getPrices());
            zhDao.updateBalance(balance, uid);
            UserAndMeasure userAndMeasure=userAndMeasureDao.findByUidAndScaleId(uid,scaleId);
            if(userAndMeasure==null){
                UserAndMeasure userAndMeasure1 = new UserAndMeasure();
                userAndMeasure1.setScaleId(scaleId);
                userAndMeasure1.setUid(uid);
                userAndMeasure1.setState(1);
                userAndMeasure1.setCount(1);
                userAndMeasureDao.save(userAndMeasure1);
            }else {
                userAndMeasureDao.updateCount(userAndMeasure.getCount()+1,uid,scaleId);
            }
                int number=scale.getNumber()+1;
                scaleDao.updateNumber(number,scaleId);

            return "success";
        }else {
            return "error";//error表示余额不足
        }
    }

    //测量的题目
    @ApiOperation("量表题目")
    @GetMapping("/problem")
    public Problems problem(Integer scaleId){
        Problems problems=new Problems();
        problems.setOptions(optionsDao.findByScaleId(scaleId));
        problems.setProblems(problemDao.findByScaleId(scaleId));
        return problems;
    }

    //返回测量分数
    @ApiOperation("测量完成后的返回结果")
    @PostMapping("/result")
    public String result(@RequestBody Result results,@RequestHeader("UID")Integer uid){
        userAndMeasureDao.updateMeasure(uid,results.getScaleId());
        String achievement=CalculateTheScore.calculate(results);
//        Achievement achievement1=new Achievement();
//        achievement1.setAchievement(achievement);
//        achievement1.setUid(uid);
//        achievement1.setScaleId(results.getScaleId());
//        achievementDao.save(achievement1);
        return achievement;
    }

    @ApiOperation("量表评论")
    @PostMapping("/comment")
    public String comment(@RequestBody CommentsTool commentsTool,@RequestHeader("UID") Integer uid){
        Comment comment=new Comment();
        comment.setComment(commentsTool.getComment());
        comment.setScaleId(commentsTool.getScaleId());
        comment.setUid(uid);
        commentDao.save(comment);
        return "success";
    }
}
