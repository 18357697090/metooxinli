package com.metoo.ps.ps.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.loongya.core.util.aliyun.OSSUtil;
import com.metoo.api.ps.PsScaleCommentApi;
import com.metoo.api.tj.TjLevelPictureApi;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.pojo.ps.model.PsScaleCommentModel;
import com.metoo.pojo.ps.vo.PsScaleCommentVo;
import com.metoo.pojo.tj.model.TjLevelPictureModel;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.ps.ps.dao.entity.PsScaleComment;
import com.metoo.ps.ps.service.PsScaleCommentService;
import com.metoo.tools.CommentsTool;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 心理测量量表用户评论表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class PsScaleCommentApiImpl implements PsScaleCommentApi {

    @Autowired
    private PsScaleCommentService psScaleCommentService;

    @DubboReference
    private TjUserInfoApi tjUserInfoApi;
    @DubboReference
    private TjLevelPictureApi tjLevelPictureApi;

    @Override
    public RE addComment(PsScaleCommentVo vo) {
        PsScaleComment comment=new PsScaleComment();
        comment.setComment(vo.getComment());
        comment.setScaleId(vo.getScaleId());
        comment.setCreateTime(new Date());
        comment.setUid(vo.getUserId());
        psScaleCommentService.save(comment);
        return RE.ok();
    }

    @Override
    public RE getCommentList(PsScaleCommentVo vo) {
        Pageable pageable= PageRequest.of(vo.getPagenum()-1,vo.getPagesize());
        List<PsScaleComment> commentList=  psScaleCommentService.findByScaleId(vo.getScaleId(),pageable);
        if(OU.isBlack(commentList)) {
            return RE.noData();
        }
        List<PsScaleCommentModel> modelList = new ArrayList<>();
        for (PsScaleComment a : commentList ) {
            PsScaleCommentModel model=new PsScaleCommentModel();
            TjUserInfoModel userInfo = tjUserInfoApi.findByUid(a.getUid());
            model.setComment(a.getComment());
            model.setUsername(userInfo.getNickName());
            model.setUserPicture(OSSUtil.fillPath(userInfo.getHeadImg()));
            TjLevelPictureModel byLevel = tjLevelPictureApi.findByLevel(userInfo.getLevel());
            if(OU.isNotBlack(byLevel)){
                model.setLevelPicture(OSSUtil.fillPath(byLevel.getLevelPicture()));
            }
            model.setCreateTime(a.getCreateTime());
            modelList.add(model);
        }
       return RE.ok(modelList);
    }
}
