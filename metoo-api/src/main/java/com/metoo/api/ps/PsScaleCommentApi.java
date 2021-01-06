package com.metoo.api.ps;

import com.loongya.core.util.RE;
import com.metoo.pojo.ps.vo.PsScaleCommentVo;
import com.metoo.tools.CommentsTool;

/**
 * <p>
 * 心理测量量表用户评论表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsScaleCommentApi {

    RE addComment(PsScaleCommentVo vo);

}
