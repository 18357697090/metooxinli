package com.metoo.ps.ps.api;

import com.loongya.core.util.RE;
import com.metoo.api.ps.PsScaleDetailApi;
import com.metoo.api.tj.TjLevelPictureApi;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.pojo.old.model.Comments;
import com.metoo.pojo.old.model.ScaleDetails;
import com.metoo.pojo.tj.model.TjUserAccountModel;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.ps.ps.dao.entity.PsComment;
import com.metoo.ps.ps.dao.entity.PsScale;
import com.metoo.ps.ps.dao.entity.PsScaleDetail;
import com.metoo.ps.ps.dao.entity.PsUserAndMeasure;
import com.metoo.ps.ps.service.PsCommentService;
import com.metoo.ps.ps.service.PsScaleDetailService;
import com.metoo.ps.ps.service.PsScaleService;
import com.metoo.ps.ps.service.PsUserAndMeasureService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 心理测量量表详情表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
public class PsScaleDetailApiImpl implements PsScaleDetailApi {

    @Autowired
    private PsScaleDetailService psScaleDetailService;

    @Autowired
    private PsScaleService psScaleService;

    @Autowired
    private PsCommentService psCommentService;

    @Autowired
    private PsUserAndMeasureService psUserAndMeasureService;

    @DubboReference
    private TjUserInfoApi tjUserInfoApi;

    @DubboReference
    private TjLevelPictureApi tjLevelPictureApi;

    @DubboReference
    private TjUserAccountApi tjUserAccountApi;

    @Override
    public RE gaugedetails(Integer scaleId, Integer uid) {

        ScaleDetails scaleDetails =new ScaleDetails();
        PsScale scale=psScaleService.findByScaleId(scaleId);
        if(scale==null){
            return RE.ok("error");
        }
        scaleDetails.setTitle(scale.getName());
        scaleDetails.setContent(scale.getContent());
        scaleDetails.setNumberOfProblem(Integer.parseInt(scale.getNumber()));
        PsScaleDetail scaleDetail= psScaleDetailService.findByScaleId(scaleId);
        if(scaleDetail==null){
            return RE.ok("error");
        }
        scaleDetails.setTopPicture(scaleDetail.getTopPicture());
        scaleDetails.setScaleNeedToKnow(scaleDetail.getScaleNeedToKnow());
        scaleDetails.setContentPicture(scaleDetail.getContentPicture());
        scaleDetails.setPrice(new BigDecimal(scale.getPrices()));
        scaleDetails.setNumberOfProblem(scale.getNumberOfProblem());
        Pageable pageable= PageRequest.of(0,3);
        List<PsComment> comments=  psCommentService.findByScaleId(scaleId,pageable);
        if (comments==null){
            return RE.ok("error");
        }
        List<Comments> comments1 = new ArrayList<>();
        for (PsComment a : comments ) {
            Comments comments2=new Comments();
            TjUserInfoModel userInfo = tjUserInfoApi.findByUid(a.getUid());
            comments2.setComment(a.getComment());
            comments2.setUsername(userInfo.getName());
            comments2.setUserPicture(userInfo.getPicture());
            comments2.setLevelPicture(tjLevelPictureApi.findByLevel(userInfo.getDw()).getLevelPicture());
            comments2.setCreateTime(a.getCreationTime());
            comments1.add(comments2);
        }
        scaleDetails.setComments(comments1);
        PsUserAndMeasure userAndMeasure= psUserAndMeasureService.findByUidAndScaleId(uid,scaleId);
        TjUserAccountModel zh=tjUserAccountApi.findByUid(uid);
        scaleDetails.setAccountBalance(zh.getBalance());
        if(userAndMeasure==null){
            scaleDetails.setState("success");
            return RE.ok("success");
        }else if(userAndMeasure.getState()==1){
            scaleDetails.setState("exist");
            return RE.ok("exist"); //表示该玩家付费但没做完
        }else if(userAndMeasure.getState()==2){
            scaleDetails.setState("done");
            return RE.ok("done"); //表示玩家已经做过了
        }else {
            scaleDetails.setState("success");
            return RE.ok("success");
        }
    }
}
