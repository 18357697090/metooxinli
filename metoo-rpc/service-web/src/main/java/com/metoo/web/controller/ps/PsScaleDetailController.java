package com.metoo.web.controller.ps;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 心理测量量表详情表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/ps-scale-detail")
public class PsScaleDetailController {

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

}
