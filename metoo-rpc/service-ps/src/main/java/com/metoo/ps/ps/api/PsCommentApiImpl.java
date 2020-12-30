package com.metoo.ps.ps.api;

import com.loongya.core.util.RE;
import com.metoo.api.ps.PsCommentApi;
import com.metoo.ps.ps.dao.entity.PsComment;
import com.metoo.ps.ps.service.PsCommentService;
import com.metoo.tools.CommentsTool;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 心理测量量表用户评论表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public class PsCommentApiImpl implements PsCommentApi {

    @Autowired
    private PsCommentService psCommentService;

    @Override
    public RE comment(CommentsTool commentsTool, Integer uid) {
        PsComment comment=new PsComment();
        comment.setComment(commentsTool.getComment());
        comment.setScaleId(commentsTool.getScaleId());
        comment.setUid(uid);
        psCommentService.save(comment);
        return RE.ok();
    }
}
