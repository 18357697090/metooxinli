package com.metoo.ps.ps.api;

import com.loongya.core.util.CommsEnum;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.loongya.core.util.aliyun.OSSUtil;
import com.metoo.api.ps.PsScaleDetailApi;
import com.metoo.api.tj.TjLevelPictureApi;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.pojo.login.enums.AuthEnum;
import com.metoo.pojo.old.model.Comments;
import com.metoo.pojo.old.model.ScaleDetails;
import com.metoo.pojo.ps.model.PsScaleCommentModel;
import com.metoo.pojo.ps.model.PsScaleDetailModel;
import com.metoo.pojo.tj.model.TjLevelPictureModel;
import com.metoo.pojo.tj.model.TjUserAccountModel;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.ps.ps.dao.entity.PsScaleComment;
import com.metoo.ps.ps.dao.entity.PsScale;
import com.metoo.ps.ps.dao.entity.PsScaleDetail;
import com.metoo.ps.ps.dao.entity.PsScaleMeasureRecord;
import com.metoo.ps.ps.service.PsScaleCommentService;
import com.metoo.ps.ps.service.PsScaleDetailService;
import com.metoo.ps.ps.service.PsScaleMeasureRecordService;
import com.metoo.ps.ps.service.PsScaleService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class PsScaleDetailApiImpl implements PsScaleDetailApi {

    @Autowired
    private PsScaleDetailService psScaleDetailService;

    @Autowired
    private PsScaleService psScaleService;

    @Autowired
    private PsScaleCommentService psScaleCommentService;

    @Autowired
    private PsScaleMeasureRecordService psScaleMeasureRecordService;

    @DubboReference
    private TjUserInfoApi tjUserInfoApi;

    @DubboReference
    private TjLevelPictureApi tjLevelPictureApi;

    @DubboReference
    private TjUserAccountApi tjUserAccountApi;

    @Override
    public RE gaugedetails(Integer scaleId, Integer uid) {
        PsScale scale=psScaleService.findByScaleId(scaleId);
        if(OU.isBlack(scale)){
            return RE.fail(CommsEnum.NO_DATA);
        }
        PsScaleDetailModel model =new PsScaleDetailModel();
        model.setTitle(scale.getName());
        model.setContent(scale.getContent());
        model.setNumberOfPeople(scale.getNumber());
        PsScaleDetail scaleDetail= psScaleDetailService.findByScaleId(scaleId);
        if(OU.isBlack(scaleDetail)){
            return RE.fail(CommsEnum.NO_DATA);
        }
        model.setTopPicture(OSSUtil.fillPath(scaleDetail.getTopPicture()));
        model.setScaleNeedToKnow(scaleDetail.getScaleNeedToKnow());
        model.setContentPicture(OSSUtil.fillPath(scaleDetail.getContentPicture()));
        model.setPrice(scale.getPrices());
        model.setNumberOfProblem(scale.getNumberOfProblem());
        Pageable pageable= PageRequest.of(0,3);
        List<PsScaleComment> comments=  psScaleCommentService.findByScaleId(scaleId,pageable);
        if(OU.isBlack(scaleDetail)){
            return RE.fail(CommsEnum.NO_DATA);
        }
        List<PsScaleCommentModel> comments1 = new ArrayList<>();
        for (PsScaleComment a : comments ) {
            PsScaleCommentModel comments2=new PsScaleCommentModel();
            TjUserInfoModel userInfo = tjUserInfoApi.findByUid(a.getUid());
            comments2.setComment(a.getComment());
            comments2.setUsername(userInfo.getNickName());
            comments2.setUserPicture(userInfo.getHeadImg());
            TjLevelPictureModel byLevel = tjLevelPictureApi.findByLevel(userInfo.getLevel());
            if(OU.isNotBlack(byLevel)){
                comments2.setLevelPicture(OSSUtil.fillPath(byLevel.getLevelPicture()));
            }
            comments2.setCreateTime(a.getCreateTime());
            comments1.add(comments2);
        }
        model.setComments(comments1);
        PsScaleMeasureRecord userAndMeasure= psScaleMeasureRecordService.findByUidAndScaleId(uid,scaleId);
        TjUserAccountModel zh=tjUserAccountApi.findByUid(uid);
        if(OU.isNotBlack(zh)){
            model.setAccountBalance(zh.getBalance());
        }
        if(userAndMeasure==null){
            model.setState("success");
        }else if(userAndMeasure.getState()==1){
            model.setState("exist"); //表示该玩家付费但没做完
        }else if(userAndMeasure.getState()==2){
            model.setState("done"); //表示玩家已经做过了
        }else {
            model.setState("success");
        }
        return RE.ok(model);
    }
}
