package com.metoo.ps.ps.api;

import com.loongya.core.util.RE;
import com.metoo.api.ps.PsScaleCommentApi;
import com.metoo.pojo.ps.vo.PsScaleCommentVo;
import com.metoo.ps.ps.dao.entity.PsScaleComment;
import com.metoo.ps.ps.service.PsScaleCommentService;
import com.metoo.tools.CommentsTool;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public RE addComment(PsScaleCommentVo vo) {
        PsScaleComment comment=new PsScaleComment();
        comment.setComment(vo.getComment());
        comment.setScaleId(vo.getScaleId());
        comment.setUid(vo.getUserId());
        psScaleCommentService.save(comment);
        return RE.ok();
    }
}
