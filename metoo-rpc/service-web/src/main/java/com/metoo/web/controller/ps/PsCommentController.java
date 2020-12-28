package com.metoo.web.controller.ps;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 心理测量量表用户评论表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/ps-comment")
public class PsCommentController {
    @ApiOperation("量表评论")
    @PostMapping("/comment")
    public String comment(@RequestBody CommentsTool commentsTool, @RequestHeader("UID") Integer uid){
        Comment comment=new Comment();
        comment.setComment(commentsTool.getComment());
        comment.setScaleId(commentsTool.getScaleId());
        comment.setUid(uid);
        commentDao.save(comment);
        return "success";
    }

}
